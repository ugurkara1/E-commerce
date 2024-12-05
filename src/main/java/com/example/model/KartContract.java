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

@Document(collection = "kartlar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KartContract {

    @Id
    @Pattern(regexp = "^\\d{16}$", message = "Kart numarası 16 haneli olmalıdır")
    private String kartNo;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 4, max = 4, message = "Şifre 4 karakterli olmalıdır")
    private String sifre;

    @Positive(message = "Para miktarı pozitif olmalıdır")
    private double paraMiktari;

    @Pattern(regexp = "^\\d{3}$", message = "CVV 3 haneli olmalıdır")
    private String cvv;
}
