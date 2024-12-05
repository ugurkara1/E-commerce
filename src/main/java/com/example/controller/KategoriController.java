package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.model.KategoriContract;
import com.example.service.KategoriService;

import java.util.List;

@RestController
@RequestMapping("/kategori")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    // Kategorileri Listele
    @GetMapping("/list")	
    public List<KategoriContract> getCategories() {
        return kategoriService.getAllCategories();
    }

    // Kategori Ekle
    @PostMapping("/add")
    public KategoriContract addCategory(@RequestBody KategoriContract kategori) {
        return kategoriService.addCategory(kategori);
    }
}
