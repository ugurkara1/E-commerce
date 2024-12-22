package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LoginDto;
import com.example.model.MusteriContract;
import com.example.service.LoginService;
import com.example.service.MusteriService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MusteriService musteriService; // MusteriService inject edilmesi gerekiyor

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest, HttpSession session) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        boolean isAuthenticated = loginService.kullaniciGirisKontrolu(email, password);

        if (isAuthenticated) {
            Optional<MusteriContract> user = musteriService.findByEmail(email);
            if (user.isPresent()) {
                MusteriContract musteri = user.get();
                // Oturumda kullanıcı bilgilerini saklama
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", musteri.getMusteriAdiSoyadi());
                session.setAttribute("musteriId", musteri.getHesapId()); // Müşteri ID'si saklanıyor
            	System.out.println("Oturum Başlatıldı: MusteriHesap ID -> " + musteri.getHesapId());

                Map<String, String> response = new HashMap<>();
                response.put("message", "Giriş başarılı, yönlendiriliyor...");
                response.put("userName", musteri.getMusteriAdiSoyadi());

                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş başarısız, lütfen bilgilerinizi kontrol edin.");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Oturumu sonlandırma
        return ResponseEntity.ok("Çıkış başarılı.");
    }

    @GetMapping("/checkSession")
    public ResponseEntity<Map<String, Object>> checkSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        String email = (String) session.getAttribute("userEmail");
        String userName = (String) session.getAttribute("userName");
        String musteriId = (String) session.getAttribute("musteriId"); // Müşteri ID'si oturumdan alınır

        response.put("authenticated", email != null);
        response.put("email", email);
        response.put("userName", userName);
        response.put("musteriId", musteriId); // Müşteri ID'sini yanıt olarak döndür
        return ResponseEntity.ok(response);
    }
}
