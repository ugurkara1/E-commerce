package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.model.ProductContract;
import com.example.service.ProductService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
	
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService; //ürünlerle ilgili iş mantığını gerçekleştiren sınıf
    @GetMapping("/kategori")
    public List<ProductContract> getProductByKategori(@RequestParam String kategori) { //belirtilen kategoriye ait ürünleri getirir
        return productService.findByKategoriAdi(kategori);
    }
    @PostMapping("/add")
    public ResponseEntity<ProductContract> addProduct(@RequestPart("product") ProductDTO productDTO,
                                                      @RequestPart("files") List<MultipartFile> files) {
        ProductContract product = productService.addProduct(productDTO, files);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/fotograf/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            Path file = Paths.get("uploads").resolve(fileName); // Dosya yolu oluşturuluyor
            Resource resource = new UrlResource(file.toUri());// Dosya kaynağı oluşturuluyor
            if (resource.exists() || resource.isReadable()) { // Dosya mevcut mu veya okunabilir mi kontrolü
                return ResponseEntity.ok().body(resource);
            } else {
                throw new RuntimeException("Dosya bulunamadı veya okunamıyor: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Dosya yüklenemedi: " + fileName, e);
        }
    }

    
    

}
