package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.HesapDto;
import com.example.dto.SaticiHesapDto;
import com.example.service.HesapService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/saticikayit") // Burada Türkçe karakter kullanılmamalı
public class SatıcıHesapController {
	@Autowired
    private HesapService hesapService;

    @PostMapping
    public String SaticikayitOl(@Valid SaticiHesapDto saticihesapDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Formda hatalar var. Lütfen kontrol edin.");
            redirectAttributes.addFlashAttribute("saticihesapDto", saticihesapDto); // Hesap DTO'sunu geri gönder
            return "redirect:/saticikayit";
        }

        ResponseEntity<String> response = hesapService.SaticikayitOl(saticihesapDto);
        if (response.getStatusCode() != HttpStatus.OK) {
            redirectAttributes.addFlashAttribute("error", response.getBody());
            redirectAttributes.addFlashAttribute("saticihesapDto", saticihesapDto); // Hesap DTO'sunu geri gönder
            return "redirect:/saticikayit";
        }

        redirectAttributes.addFlashAttribute("message", response.getBody());
        return "redirect:/maildogrulama";
    }
    @PostMapping("/dogrulama")
    public String dogrulamaYap(@RequestParam("email") String email, 
                                @RequestParam("verificationCode") String verificationCode,
                                RedirectAttributes redirectAttributes) {
        ResponseEntity<String> response = hesapService.dogrulamaKodunuDogrula(email, verificationCode);
        
        if (response.getStatusCode() == HttpStatus.OK) {
            redirectAttributes.addFlashAttribute("message", response.getBody());
            return "redirect:/anasayfa"; // Doğrulama başarılı ise ana sayfaya yönlendir
        } else {
            redirectAttributes.addFlashAttribute("error", response.getBody());
            return "redirect:/maildogrulama"; // Hata mesajı ile doğrulama sayfasına geri dön
        }
    }
}
