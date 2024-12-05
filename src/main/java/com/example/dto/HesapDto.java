package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HesapDto {

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email adresi girin")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 8, message = "Şifre en az 8 karakter olmalıdır")
    private String sifre;

    @NotBlank(message = "Telefon boş olamaz")
    @Pattern(regexp = "^\\d{11}$", message = "Telefon numarası 11 haneli olmalıdır")
    private String telefon;

    @NotBlank(message = "Müşteri adı ve soyadı boş olamaz")
    @Size(min = 2, max = 50, message = "Müşteri adı 2 ile 50 karakter arasında olmalıdır")
    private String musteriAdiSoyadi;

    @NotBlank(message = "Cinsiyet boş olamaz")
    @Pattern(regexp = "Erkek|Kadın", message = "Cinsiyet sadece 'Erkek' veya 'Kadın' olabilir")
    private String cinsiyet;

    @NotBlank(message = "Adres boş olamaz")
    private String adres;

    private Double boy;
    private Double kilo;
    
    //getter setter metodları
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMusteriAdiSoyadi() {
		return musteriAdiSoyadi;
	}
	public void setMusteriAdiSoyadi(String musteriAdiSoyadi) {
		this.musteriAdiSoyadi = musteriAdiSoyadi;
	}
	public String getCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public Double getBoy() {
		return boy;
	}
	public void setBoy(Double boy) {
		this.boy = boy;
	}
	public Double getKilo() {
		return kilo;
	}
	public void setKilo(Double kilo) {
		this.kilo = kilo;
	}
    
    
    
}
