package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.KartContract;
import java.util.Optional;

public interface KartRepository extends MongoRepository<KartContract, String> {
    Optional<KartContract> findByKartNo(String kartNo);
}
