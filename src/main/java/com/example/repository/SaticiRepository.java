package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.SaticiContract;
import java.util.List;
import java.util.Optional;

public interface SaticiRepository extends MongoRepository<SaticiContract, String> {
    
    // Satıcı ID'ye göre satıcı bulma
    Optional<SaticiContract> findBySaticiId(String saticiId);

    // Mağaza adına göre satıcı bulma
    Optional<SaticiContract> findByMagazaAdi(String magazaAdi);

    // Satıcı ad soyadına göre satıcı bulma
    List<SaticiContract> findBySaticiAdSoyad(String saticiAdSoyad);

    // Hesap ID'sine göre satıcı silme
    void deleteByHesapId(String hesapId);
    Optional<SaticiContract> findByEmail(String email);
    
   	Optional<SaticiContract> findByTelefon(String telefon);
}
