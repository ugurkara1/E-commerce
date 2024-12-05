package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.KartContract;

public interface KartRepository extends MongoRepository<KartContract, String> {

}
