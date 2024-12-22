package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.model.ProductContract;
import com.example.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
	
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addProduct(
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart("files") List<MultipartFile> files,
            HttpSession session) {

        String hesapId = (String) session.getAttribute("hesapId");
        if (hesapId == null) {
            System.out.println("Satıcı ID oturumda bulunamadı.");
            return ResponseEntity.status(401).body("Satıcı oturumu bulunamadı. Lütfen giriş yapın.");
        } else {
            System.out.println("Hesap Id: " + hesapId);
        }

        try {
            ProductContract product = productService.addProduct(productDTO, files, hesapId);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ürün eklenirken bir hata oluştu: " + e.getMessage());
        }
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

    @GetMapping("/urunler")
    public List<ProductContract> getProductsBySeller(@RequestParam String hesapId) {
        return productService.findProductsBySeller(hesapId);
    }
    @DeleteMapping("/sil/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        try {
            productService.getProductById(productId);
            return ResponseEntity.ok("Ürün başarıyla silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ürün silinirken bir hata oluştu: " + e.getMessage());
        }
    }
    
    

}
