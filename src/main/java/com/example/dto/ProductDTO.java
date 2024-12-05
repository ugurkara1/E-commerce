package com.example.dto;

public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private String kategoriAdi; // Kategori adı ile eşleşiyor

    // Getter ve Setter'lar
    // Parametreli kurucu
    public ProductDTO(String name, String description, double price, String kategoriAdi) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.kategoriAdi = kategoriAdi;
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
