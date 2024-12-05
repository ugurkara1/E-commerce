package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LoginDto;
import com.example.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest, HttpSession session) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        boolean isAuthenticated = loginService.kullaniciGirisKontrolu(email, password);

        if (isAuthenticated) {
            session.setAttribute("userEmail", email); // Kullanıcı e-posta adresini oturumda saklıyoruz
            return ResponseEntity.ok("Giriş başarılı, yönlendiriliyor...");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş başarısız, lütfen bilgilerinizi kontrol edin.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Oturumu sonlandırma
        return ResponseEntity.ok("Çıkış başarılı.");
    }

    @GetMapping("/checkSession")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        String email = (String) session.getAttribute("userEmail");
        response.put("authenticated", email != null);
        response.put("email", email);
        return ResponseEntity.ok(response);
    }
}
