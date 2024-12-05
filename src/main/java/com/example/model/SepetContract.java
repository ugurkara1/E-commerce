package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Document(collection = "sepetler")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SepetContract {

    @Id
    private String sepetId;
    private double toplamFiyat;
    private List<ProductContract> urunler;
}
