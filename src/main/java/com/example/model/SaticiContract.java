package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "saticilar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaticiContract extends HesapContract {

    private String saticiId;

    @NotBlank(message = "Mağaza adı boş olamaz")
    @Size(min = 2, max = 100, message = "Mağaza adı 2 ile 100 karakter arasında olmalıdır")
    private String magazaAdi;

    @NotBlank(message = "Adres boş olamaz")
    @Size(max = 200, message = "Adres en fazla 200 karakter olabilir")
    private String adres;
    

    @NotBlank(message = "Satıcı ad soyad boş olamaz")
    @Size(min = 2, max = 50, message = "Satıcı adı 2 ile 50 karakter arasında olmalıdır")
    private String saticiAdSoyad;


	public String getSaticiId() {
		return saticiId;
	}


	public void setSaticiId(String saticiId) {
		this.saticiId = saticiId;
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


	public String getSaticiAdSoyad() {
		return saticiAdSoyad;
	}


	public void setSaticiAdSoyad(String saticiAdSoyad) {
		this.saticiAdSoyad = saticiAdSoyad;
	}
    
    
}

