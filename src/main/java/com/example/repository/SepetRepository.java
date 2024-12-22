package com.example.repository;

import com.example.model.SepetContract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SepetRepository extends MongoRepository<SepetContract, String> {
}
