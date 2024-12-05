package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "musteriler")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusteriContract extends HesapContract {

	private String musteriId;

    @NotBlank(message = "Müşteri adı ve soyadı boş olamaz")
    @Size(min = 2, max = 50, message = "Müşteri adı 2 ile 50 karakter arasında olmalıdır")
    private String musteriAdiSoyadi;

    @NotBlank(message = "Cinsiyet boş olamaz")
    @Pattern(regexp = "Erkek|Kadın", message = "Cinsiyet sadece 'Erkek' veya 'Kadın' olabilir")
    private String cinsiyet;

    @NotBlank(message = "Adres boş olamaz")
    private String adres;

    @Positive(message = "Boy pozitif bir değer olmalıdır")
    private Double boy;


    @Positive(message = "Kilo pozitif bir değer olmalıdır")
    private Double kilo;

	public String getMusteriId() {
		return musteriId;
	}

	public void setMusteriId(String musteriId) {
		this.musteriId = musteriId;
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

	public double getBoy() {
		return boy;
	}

	public void setBoy(double boy) {
		this.boy = boy;
	}

	public double getKilo() {
		return kilo;
	}

	public void setKilo(double kilo) {
		this.kilo = kilo;
	}
    
    
}

