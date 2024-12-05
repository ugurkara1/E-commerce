package com.example.repository;

import com.example.model.KategoriContract;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface KategoriRepository extends MongoRepository<KategoriContract, String> {
    // Kategori adını kullanarak kategori bulma metodu
    Optional<KategoriContract> findByName(String name);
}
