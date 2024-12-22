package com.example.controller;

import com.example.model.ProductContract;
import com.example.model.SepetContract;
import com.example.service.SepetService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sepet")
public class SepetController {

    @Autowired
    private SepetService sepetService;

    @PostMapping("/ekle")
    public ResponseEntity<?> sepeteEkle(@RequestParam String productId, HttpSession session) {
        String musteriId = (String) session.getAttribute("musteriId");
        if (musteriId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Oturum açmamışsınız.");
        }

        try {
            sepetService.sepeteUrunEkle(musteriId, productId);
            return ResponseEntity.ok().body("Ürün sepete başarıyla eklendi!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Sepete ekleme sırasında bir hata oluştu: " + e.getMessage());
        }
    }


    @GetMapping
    public String sepetGoruntule(HttpSession session, Model model) {
        String musteriId = (String) session.getAttribute("musteriId");
        if (musteriId == null) {
            return "redirect:/auth/login";
        }

        SepetContract sepet = sepetService.getSepet(musteriId);
        model.addAttribute("cartItems", sepet.getUrunler());
        model.addAttribute("totalPrice", sepet.getToplamFiyat());
        return "sepet";
    }

    @DeleteMapping("/sil/{productId}")
    public ResponseEntity<?> sepettenKaldir(@PathVariable String productId, HttpSession session) {
        String musteriId = (String) session.getAttribute("musteriId");
        if (musteriId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Oturum açmamışsınız.");
        }

        try {
            sepetService.sepetteUrunSil(musteriId, productId);
            return ResponseEntity.ok("Ürün sepetten başarıyla kaldırıldı!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ürün kaldırma sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    @PostMapping("/tamamla")
    public ResponseEntity<String> siparisiTamamla() {
        try {
            String sonuc = sepetService.siparisiTamamla();
            return ResponseEntity.ok(sonuc);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Sipariş tamamlama sırasında bir hata oluştu: " + e.getMessage());
        }
    }
}
