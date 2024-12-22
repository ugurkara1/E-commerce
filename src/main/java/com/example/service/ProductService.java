package com.example.service;

import com.example.dto.ProductDTO;
import com.example.model.ProductContract;
import com.example.model.SepetContract;
import com.example.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    //fotoğraf url leri için bir temel
    private final String BASE_URL = "http://localhost:8080/api/products/fotograf/";

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //ürün eklemek için kod parçası
    public ProductContract addProduct(ProductDTO productDTO, List<MultipartFile> files,String hesapId) {
        ProductContract product = new ProductContract();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setKategoriAdi(productDTO.getKategoriAdi());
        product.setStockadedi(productDTO.getStockadedi());
        product.setHesapId(hesapId); // Satıcı ID'si set ediliyor

        //fotoğrafları yükler ve url leri ayarlar
        if (!files.isEmpty()) {
            List<String> photoUrls = files.stream() //listedeki her öğeyi sırasıyla işlemek için
                .map(this::saveFile)
                .map(fileName -> BASE_URL + fileName)
                .collect(Collectors.toList());
            product.setPhotoUrls(photoUrls);
        }

        return productRepository.save(product);
    }
    
    private String saveFile(MultipartFile file) { //multipartfile yazarak çoklu dosya tanımladım
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try {
            // "uploads" dizininde dosyayı oluştur ve kaydet.

            Path filePath = Paths.get("uploads", fileName);
            Files.createDirectories(filePath.getParent()); //Dosyanın kaydedileceği dizinin var olup olmadığını kontrol etmek ve yoksa oluşturmak.
            Files.write(filePath, file.getBytes()); //dosya yazma 
            return fileName; 
        } catch (IOException e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + e.getMessage());
        }
    }

    public List<ProductContract> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductContract getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }
    
    //Kategoriye göre ürün listelemeyi sağlayan kod parçası
    public List<ProductContract> findByKategoriAdi(String kategoriAdi) {
        return productRepository.findByKategoriAdi(kategoriAdi)
                .stream() //veri üzerinde işlem yapmak için kullanılan API
                .map(urun -> new ProductContract(  //map:veri akışında(stream) bulunan öğeyi bir dönüşümden geçirerek yeni bir öğe oluşturur.
                        urun.getId(),            // Veritabanındaki ID
                        urun.getName(),
                        urun.getDescription(),
                        urun.getPrice(),
                        urun.getPhotoUrls(),     // Fotoğraflar
                        urun.getStockadedi()
                ))
                .collect(Collectors.toList()); //çıkan sonuçları liste olarak toplar 
    }
    public ProductContract sellProduct(String id, int quantity) {
        ProductContract product = productRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Ürün bulunamadı"));

        if (product.getStockadedi() < quantity) {
            throw new RuntimeException("Yeterli stok yok!");
        }

        product.setStockadedi(product.getStockadedi() - quantity); // Stok düşülüyor
        return productRepository.save(product);
    }
    public List<ProductContract> findProductsBySeller(String hesapId) {
        return productRepository.findByHesapId(hesapId)
        		.stream() //veri üzerinde işlem yapmak için kullanılan API
                .map(urun -> new ProductContract(  //map:veri akışında(stream) bulunan öğeyi bir dönüşümden geçirerek yeni bir öğe oluşturur.
                        urun.getId(),            // Veritabanındaki ID
                        urun.getName(),
                        urun.getDescription(),
                        urun.getPrice(),
                        urun.getPhotoUrls(),     // Fotoğraflar
                        urun.getStockadedi()
                ))
                .collect(Collectors.toList()); //çıkan sonuçları liste olarak toplar 
    }
    public void deleteProductById(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Silinmek istenen ürün bulunamadı.");
        }
        productRepository.deleteById(productId);
    }
    public void addCommentToProduct(String productId, ProductContract.Comment comment) {
        ProductContract product = getProductById(productId);
        if (product != null) {
            // Eğer comments null ise, boş bir liste oluşturulmalı
            if (product.getComments() == null) {
                product.setComments(new ArrayList<>());
            }
            
            List<ProductContract.Comment> comments = product.getComments();
            comments.add(comment); // Yeni yorumu listeye ekle
            product.setComments(comments); // Listeyi tekrar set et
            productRepository.save(product); // Güncellenen ürünü kaydet
        }
    }



}
