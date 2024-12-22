package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.LoginDto;
import com.example.model.SaticiContract;
import com.example.service.LoginService;
import com.example.service.SaticiLoginService;
import com.example.service.SaticiService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/saticiGirisPanel")
public class SaticiLoginController {

    @Autowired
    private SaticiService saticiService;

    @PostMapping("/saticiGirisPanel")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest, HttpSession session) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        boolean isAuthenticated = saticiService.kullaniciGirisKontrolu(email, password);

        if (isAuthenticated) {
            Optional<SaticiContract> user = saticiService.findByEmail(email);
            if (user.isPresent()) {
                SaticiContract satici = user.get();
                // Oturumda kullanıcı bilgilerini saklama
                session.setAttribute("userEmail", email);
                session.setAttribute("magazaAdi", satici.getMagazaAdi());
                session.setAttribute("hesapId", satici.getHesapId()); // Müşteri ID'si saklanıyor
                // Konsolda kontrol için loglama
            	System.out.println("Oturum Başlatıldı: Satıcı ID -> " + satici.getHesapId());
                Map<String, String> response = new HashMap<>();
                response.put("message", "Giriş başarılı, yönlendiriliyor...");
                response.put("magazaAdi", satici.getMagazaAdi());
                response.put("hesapId", String.valueOf(satici.getHesapId()));  // Hesap ID'si eklenmeli


                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş başarısız, lütfen bilgilerinizi kontrol edin.");
    }
    

}
