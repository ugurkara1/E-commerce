package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
public class ProductContract {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String kategoriAdi;
    private List<String> photoUrls; // Birden fazla fotoğraf için liste
    // Parametresiz Constructor (Gerekli!)
    public ProductContract() {
    	
    }
    // Parameteril constructor
    public ProductContract(String id, String name, String description, double price, List<String> photoUrls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photoUrls = photoUrls;
    }


    // Getter ve Setter'lar
    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }
}
	