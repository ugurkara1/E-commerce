package com.example.service;
import com.example.model.HesapContract;
import com.example.model.MusteriContract;
import com.example.repository.HesapRepository;
import com.example.repository.MusteriRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

	@Autowired
    private MusteriRepository musteriRepository;

    public boolean kullaniciGirisKontrolu(String email, String sifre) {
        // Veritabanından email'e göre kullanıcıyı bul
        Optional<MusteriContract> user = musteriRepository.findByEmail(email);

        // Kullanıcı mevcutsa ve BCrypt ile şifre doğrulaması başarılıysa true döndür
        return user.isPresent() && BCrypt.checkpw(sifre, user.get().getSifre());
    }

}

