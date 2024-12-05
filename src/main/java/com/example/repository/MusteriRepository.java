package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.HesapContract;
import com.example.model.MusteriContract;
import java.util.List;
import java.util.Optional;

public interface MusteriRepository extends MongoRepository<MusteriContract, String> {
    
    // Müşteri ID'ye göre müşteri bulma
    Optional<MusteriContract> findByMusteriId(String musteriId);

    // Müşteri adına göre müşteri listeleme
    List<MusteriContract> findByMusteriAdiSoyadi(String musteriAdiSoyadi);

    // Cinsiyete göre müşteri listeleme
    List<MusteriContract> findByCinsiyet(String cinsiyet);

    // Hesap ID'sine göre müşteri silme
    void deleteByHesapId(String hesapId);
    //Optional<MusteriContract> findByEmail(String email);
 
	Optional<MusteriContract> findByTelefon(String telefon);
    Optional<MusteriContract> findByEmail(String email);



}
