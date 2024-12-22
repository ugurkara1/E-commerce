package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.model.ProductContract;
import com.example.model.SaticiContract;
import com.example.service.ProductService;
import com.example.service.SaticiService;

@Controller
public class MagazaController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private SaticiService saticiService;
    @GetMapping("/magaza")
    public String magaza(@RequestParam("hesapId") String hesapId, Model model) {
        // Hesap ID'sine göre satıcıyı bulma
        Optional<SaticiContract> saticiOptional = saticiService.findByHesapId(hesapId);

        if (saticiOptional.isPresent()) {
            SaticiContract satici = saticiOptional.get();
            model.addAttribute("satici", satici);  // Model'e satıcıyı ekliyoruz
        } else {
            model.addAttribute("error", "Satıcı bulunamadı.");
        }

        // Ürünleri model'e ekleyin
        List<ProductContract> products = productService.findProductsBySeller(hesapId);
        model.addAttribute("products", products);

        return "magaza";  // Görünüm adı
    }





}
