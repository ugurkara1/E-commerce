package com.example.controller;
import com.example.model.ProductContract;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UrunDetayController {
	@Autowired
    private ProductService productService;

	@GetMapping("/urunDetay")
	public String urunDetay(@RequestParam("id") String id, Model model) {
	    ProductContract product = productService.getProductById(id); //id' ye göre ürün detayı gösterir
	    if (product == null) {
	        return "error"; // Eğer ürün bulunamazsa error.html gösterilebilir.
	    }
	    model.addAttribute("product", product);
	    return "urunDetay"; // urunDetay.html sayfasına yönlendirme
	}
	@PostMapping("/api/products/urunDetay/yorum/{productId}")
	public ResponseEntity<String> addComment(
	        @PathVariable("productId") String productId,
	        @RequestParam("username") String username,
	        @RequestParam("comment") String commentText,
	        @RequestParam("puan") int puan) {

	    // Ürün ID'sinin doğruluğunu kontrol et
	    ProductContract product = productService.getProductById(productId);
	    if (product == null) {
	        return ResponseEntity.badRequest().body("Geçersiz ürün ID'si veya ürün bulunamadı.");
	    }

	    if (puan < 1 || puan > 5) {
	        return ResponseEntity.badRequest().body("Geçersiz puan! Puan 1 ile 5 arasında olmalıdır.");
	    }

	    ProductContract.Comment comment = new ProductContract.Comment(username, commentText, puan);
	    productService.addCommentToProduct(productId, comment);
	    return ResponseEntity.ok("Yorum başarıyla eklendi!");
	}


}
