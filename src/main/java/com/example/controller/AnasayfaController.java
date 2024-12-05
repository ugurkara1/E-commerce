package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.ProductContract;
import com.example.service.ProductService;

@Controller
public class AnasayfaController {

    @Autowired
    private ProductService productService;
    
    //anasayfa ekranına yönlendirme ve ürünlerin hepsini orda listelemek için gerekli yönlendirmeler
    @GetMapping("/anasayfa")
    public String Anasayfa(Model model) {
        List<ProductContract> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "anasayfa";
    }

    @GetMapping("/kategori")
    public String Kategori() {
        return "kategori"; // kategori.html dosyasına yönlendirme
    }

    @GetMapping("/uyeolmak")
    public String UyeOlmak() {
        return "uyeolmak"; // uyeolmak.html dosyasına yönlendirme
    }

    @GetMapping("/saticikayit")
    public String SaticiUye() {
        return "saticikayit"; // saticikayit.html dosyasına yönlendirme
    }

    @GetMapping("/login")
    public String Giris() {
        return "login"; // login.html dosyasına yönlendirme
    }

    @GetMapping("/saticiGirisPanel")
    public String SaticiGiris() {
        return "saticiGirisPanel";
    }

    @GetMapping("/urunekle")
    public String urunekleme() {
        return "urunekle";
    }

    @GetMapping("/maildogrulama")
    public String Mail() {
        return "maildogrulama";
    }
}
