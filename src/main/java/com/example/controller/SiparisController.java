package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.KartService;

@RestController
@RequestMapping("/siparis")
public class SiparisController {

    @Autowired
    private KartService kartService;

    @PostMapping("/odeme/kart")
    public ResponseEntity<String> kartlaOdeme(@RequestParam String kartNo, @RequestParam String sifre, @RequestParam String cvv) {
        // Kart bilgilerini doğrula ve ödeme işlemini gerçekleştir
        try {
            kartService.kartlaOdemeYap(kartNo, sifre, cvv);
            return ResponseEntity.ok("Kartla ödeme başarılı!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Kartla ödeme sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    @PostMapping("/odeme/cod")
    public ResponseEntity<String> kapidaOdeme() {
        // Kapıda ödeme işlemi yapılacak
        return ResponseEntity.ok("Kapıda ödeme başarılı!");
    }
}
