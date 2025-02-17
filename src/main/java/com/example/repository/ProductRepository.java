package com.example.repository;
	
import com.example.model.ProductContract;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
	
	@Repository
	public interface ProductRepository extends MongoRepository<ProductContract, String> {

		List<ProductContract> findByKategoriAdi(String kategoriAdi);
	    List<ProductContract> findByKategoriAdiAndStockGreaterThan(String kategoriAdi, int stock); // 'stockadedi' değil, 'stock' kullanılacak
	    List<ProductContract> findByHesapId(String hesapId);

	}
	