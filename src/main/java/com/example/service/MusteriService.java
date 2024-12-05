package com.example.service;

import com.example.model.MusteriContract;
import com.example.repository.MusteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MusteriService {

	@Autowired
    private MusteriRepository musteriRepository;

    // Yeni müşteri oluşturma
    public MusteriContract createMusteri(MusteriContract musteri) {
        return musteriRepository.save(musteri);
    }

    // Tüm müşterileri listeleme
    public List<MusteriContract> getAllMusteriler() {
        return musteriRepository.findAll();
    }

    // ID'ye göre müşteri bulma
    public Optional<MusteriContract> getMusteriById(String musteriId) {
        return musteriRepository.findByMusteriId(musteriId);
    }


    // Cinsiyete göre müşterileri listeleme
    public List<MusteriContract> getMusteriByCinsiyet(String cinsiyet) {
        return musteriRepository.findByCinsiyet(cinsiyet);
    }

    // Müşteri güncelleme
    public MusteriContract updateMusteri(MusteriContract musteri) {
        return musteriRepository.save(musteri);
    }

    // Hesap ID'sine göre müşteri silme
    public void deleteMusteri(String hesapId) {
        musteriRepository.deleteByHesapId(hesapId);
    }
}
