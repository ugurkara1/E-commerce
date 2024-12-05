package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.KategoriContract;
import com.example.repository.KategoriRepository;

@Service
public class KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    public List<KategoriContract> getAllCategories() {
        return kategoriRepository.findAll();
    }

    public KategoriContract addCategory(KategoriContract kategori) {
        return kategoriRepository.save(kategori);
    }
}

