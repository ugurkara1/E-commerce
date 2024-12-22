package com.example.service;

import com.example.model.MusteriContract;
import com.example.model.SaticiContract;
import com.example.repository.SaticiRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SaticiService {

    @Autowired
    private SaticiRepository saticiRepository;

    // Yeni satıcı oluşturma
    public SaticiContract createSatici(SaticiContract satici) {
        return saticiRepository.save(satici);
    }

    // Tüm satıcıları listeleme
    public List<SaticiContract> getAllSaticilar() {
        return saticiRepository.findAll();
    }

    // ID'ye göre satıcı bulma
    public Optional<SaticiContract> getSaticiById(String saticiId) {
        return saticiRepository.findBySaticiId(saticiId);
    }

    // Mağaza adına göre satıcı bulma
    public Optional<SaticiContract> getSaticiByMagazaAdi(String magazaAdi) {
        return saticiRepository.findByMagazaAdi(magazaAdi);
    }

    // Satıcı ad soyadına göre satıcı bulma
    public List<SaticiContract> getSaticiByAdSoyad(String saticiAdSoyad) {
        return saticiRepository.findBySaticiAdSoyad(saticiAdSoyad);
    }

    // Satıcı güncelleme
    public SaticiContract updateSatici(SaticiContract satici) {
        return saticiRepository.save(satici);
    }

    // Hesap ID'sine göre satıcı silme
    public void deleteSatici(String hesapId) {
        saticiRepository.deleteByHesapId(hesapId);
    }
    public Optional<SaticiContract> getSaticiByEmail(String email) {
        return saticiRepository.findByEmail(email);
    }
    // Kullanıcı Giriş Kontrolü
    public boolean kullaniciGirisKontrolu(String email, String sifre) {
        // Veritabanından email'e göre kullanıcıyı bul
        Optional<SaticiContract> user = saticiRepository.findByEmail(email);

        // Kullanıcı mevcutsa ve BCrypt ile şifre doğrulaması başarılıysa true döndür
        return user.isPresent() && BCrypt.checkpw(sifre, user.get().getSifre());
    }
    public Optional<SaticiContract> findByEmail(String email) {
        return saticiRepository.findByEmail(email);
    }
    // Hesap ID'sine göre satıcıyı bulma
    public Optional<SaticiContract> findByHesapId(String hesapId) {
        return saticiRepository.findByHesapId(hesapId);
    }
    
}
