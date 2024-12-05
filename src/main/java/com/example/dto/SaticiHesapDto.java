package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SaticiHesapDto {
	@NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email adresi girin")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 8, message = "Şifre en az 8 karakter olmalıdır")
    private String sifre;

    @NotBlank(message = "Telefon boş olamaz")
    @Pattern(regexp = "^\\d{11}$", message = "Telefon numarası 11 haneli olmalıdır")
    private String telefon;

    @NotBlank(message = "Satıcı adı ve soyadı boş olamaz")
    @Size(min = 2, max = 50, message = "Satıcı adı 2 ile 50 karakter arasında olmalıdır")
    private String saticiAdSoyad;
    
    @NotBlank(message = "Mağaza adı boş olamaz")
    @Size(min = 2, max = 100, message = "Mağaza adı 2 ile 100 karakter arasında olmalıdır")
    private String magazaAdi;
    
    @NotBlank(message = "Adres boş olamaz")
    @Size(max = 200, message = "Adres en fazla 200 karakter olabilir")
    private String adres;

    
    //getter ve setter metodları
    
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

	public String getSaticiAdSoyad() {
		return saticiAdSoyad;
	}

	public void setSaticiAdSoyad(String saticiAdSoyad) {
		this.saticiAdSoyad = saticiAdSoyad;
	}

	public String getMagazaAdi() {
		return magazaAdi;
	}

	public void setMagazaAdi(String magazaAdi) {
		this.magazaAdi = magazaAdi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
    
    
    

}
