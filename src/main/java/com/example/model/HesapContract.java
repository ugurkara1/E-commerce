package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "HesapContract")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HesapContract {

    @Id
    private String hesapId;

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email adresi girin")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 8, message = "Şifre en az 8 karakter olmalıdır")
    private String sifre;

    @NotBlank(message = "Telefon boş olamaz")
    @Pattern(regexp = "^\\d{11}$", message = "Telefon numarası 11 haneli olmalıdır")
    private String telefon;

	public String getHesapId() {
		return hesapId;
	}

	public void setHesapId(String hesapId) {
		this.hesapId = hesapId;
	}

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
    
    
}
