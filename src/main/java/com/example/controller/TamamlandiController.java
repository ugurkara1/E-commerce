package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TamamlandiController {

    @GetMapping("/tamamlandi")
    public String tamamlandi() {
        return "tamamlandi";  // tamamlandi.html sayfasına yönlendirir
    }
}
