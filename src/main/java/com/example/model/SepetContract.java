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
    //private int adet;
	/*public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}*/
	public String getSepetId() {
		return sepetId;
	}
	public void setSepetId(String sepetId) {
		this.sepetId = sepetId;
	}
	public double getToplamFiyat() {
		return toplamFiyat;
	}
	public void setToplamFiyat(double toplamFiyat) {
		this.toplamFiyat = toplamFiyat;
	}
	public List<ProductContract> getUrunler() {
		return urunler;
	}
	public void setUrunler(List<ProductContract> urunler) {
		this.urunler = urunler;
	}
	// Parametreli constructor ekliyoruz
	public SepetContract(String sepetId, double toplamFiyat, List<ProductContract> urunler) {
	    this.sepetId = sepetId;
	    this.toplamFiyat = toplamFiyat;
	    this.urunler = urunler;
	    //this.adet = (adet > 0) ? adet : 1; // Varsayılan olarak 1 kullanabilirsiniz
	}

    
}
