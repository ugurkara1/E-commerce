package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.SepetContract;

public interface SepetRepository extends MongoRepository<SepetContract, String>{

}
