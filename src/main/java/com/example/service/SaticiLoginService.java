package com.example.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MusteriContract;
import com.example.model.SaticiContract;
import com.example.repository.MusteriRepository;
import com.example.repository.SaticiRepository;
@Service
public class SaticiLoginService {
	@Autowired
    private SaticiRepository saticiRepository;

    public boolean kullaniciGirisKontrolu(String email, String sifre) {
        // Veritabanından email'e göre kullanıcıyı bul
        Optional<SaticiContract> user = saticiRepository.findByEmail(email);

        // Kullanıcı mevcutsa ve BCrypt ile şifre doğrulaması başarılıysa true döndür
        return user.isPresent() && BCrypt.checkpw(sifre, user.get().getSifre());
    }
}
