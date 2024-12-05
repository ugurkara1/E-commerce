//package com.example.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Positive;
//import jakarta.validation.constraints.Size;
//import lombok.Data;
//import org.springframework.web.multipart.MultipartFile;
//
//public class UrunDTO {
//    @NotBlank(message = "Ürün ismi boş olamaz")
//    private String urunIsmi;
//
//    private String urunAciklama;
//
//    @NotBlank(message = "Ürün rengi boş olamaz")
//    @Size(max = 30, message = "Ürün rengi en fazla 30 karakter olabilir")
//    private String urunRenk;
//
//    @Positive(message = "Ürün fiyatı pozitif olmalıdır")
//    private double urunFiyat;
//
//    private boolean garanti;
//
//    @Positive(message = "Ürün adedi pozitif olmalıdır")
//    private int urunAdet;
//
//    private MultipartFile urunFotograf;  // Fotoğraf dosyası
//
//    @NotBlank(message = "Kategori adı boş olamaz")
//    @Size(min = 2, max = 50, message = "Kategori adı 2 ile 50 karakter arasında olmalıdır")
//    private String kategoriAdi;
//
//    // Getter ve Setter
//
//    public String getKategoriAdi() {
//        return kategoriAdi;
//    }
//
//    public void setKategoriAdi(String kategoriAdi) {
//        this.kategoriAdi = kategoriAdi;
//    }
//
//    public String getUrunIsmi() {
//        return urunIsmi;
//    }
//
//    public void setUrunIsmi(String urunIsmi) {
//        this.urunIsmi = urunIsmi;
//    }
//
//    public String getUrunRenk() {
//        return urunRenk;
//    }
//
//    public void setUrunRenk(String urunRenk) {
//        this.urunRenk = urunRenk;
//    }
//
//    public double getUrunFiyat() {
//        return urunFiyat;
//    }
//
//    public void setUrunFiyat(double urunFiyat) {
//        this.urunFiyat = urunFiyat;
//    }
//
//    public boolean isGaranti() {
//        return garanti;
//    }
//
//    public void setGaranti(boolean garanti) {
//        this.garanti = garanti;
//    }
//
//    public int getUrunAdet() {
//        return urunAdet;
//    }
//
//    public void setUrunAdet(int urunAdet) {
//        this.urunAdet = urunAdet;
//    }
//
//    public MultipartFile getUrunFotograf() {
//        return urunFotograf;
//    }
//
//    public void setUrunFotograf(MultipartFile urunFotograf) {
//        this.urunFotograf = urunFotograf;
//    }
//
//    public String getUrunAciklama() {
//        return urunAciklama;
//    }
//
//    public void setUrunAciklama(String urunAciklama) {
//        this.urunAciklama = urunAciklama;
//    }
//}
