package com.example.controller;

import com.example.dto.HesapDto;
import com.example.service.HesapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/uyeolmak")
public class HesapController {
    
    @Autowired
    private HesapService hesapService;

    @PostMapping
    public String kayitOl(@Valid HesapDto hesapDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Formda hatalar var. Lütfen kontrol edin.");
            redirectAttributes.addFlashAttribute("hesapDto", hesapDto); // Hesap DTO'sunu geri gönder
            return "redirect:/uyeolmak";
        }

        ResponseEntity<String> response = hesapService.kayitOl(hesapDto);
        if (response.getStatusCode() != HttpStatus.OK) {
            redirectAttributes.addFlashAttribute("error", response.getBody());
            redirectAttributes.addFlashAttribute("hesapDto", hesapDto); // Hesap DTO'sunu geri gönder
            return "redirect:/uyeolmak";
        }

        redirectAttributes.addFlashAttribute("message", response.getBody());
        return "redirect:/maildogrulama";  // Doğrulama ekranına yönlendir
    }

    @PostMapping("/maildogrulama")
    public String dogrulamaYap(@RequestParam("email") String email, 
                               @RequestParam("verificationCode") String verificationCode,
                               RedirectAttributes redirectAttributes) {
        
        ResponseEntity<String> response = hesapService.dogrulamaKodunuDogrula(email, verificationCode);

        if (response.getStatusCode() == HttpStatus.OK) {
            redirectAttributes.addFlashAttribute("message", response.getBody());
            return "redirect:/anasayfa";  // Başarılı doğrulama sonrası ana sayfaya yönlendir
        } else {
            redirectAttributes.addFlashAttribute("error", response.getBody());
            return "redirect:/maildogrulama"; // Hata mesajı ile doğrulama sayfasına geri dön
        }
    }
}


