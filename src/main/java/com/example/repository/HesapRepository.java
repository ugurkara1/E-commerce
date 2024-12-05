package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.HesapDto;
import com.example.model.HesapContract;
import com.example.model.MusteriContract;

import java.util.Optional;
import java.util.List;

@Repository
public interface HesapRepository extends MongoRepository<HesapContract, String> {
    
    // Email'e göre hesap bulma
    Optional<HesapContract> findByEmail(String email);
	List<HesapContract> findByTelefon(String telefon);

    // Telefon numarasına göre hesap bulma
    //Optional<HesapContract> findByTelefon(String telefon);

    // Hesap ID'sine göre hesap silme
    void deleteByHesapId(String hesapId);
    Optional<MusteriContract> findByEmailAndSifre(String email, String sifre);
    
}
