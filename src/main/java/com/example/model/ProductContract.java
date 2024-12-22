package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
public class ProductContract {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String kategoriAdi;
    private int stock;
    private List<String> photoUrls; // Birden fazla fotoğraf için liste
    private List<Comment> comments; // Yorumlar listesi

    public List<Comment> getComments(){
    	return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public static class Comment {
        private String username;
        private String comment;
        private int puan;  // Değerlendirme puanı

        // Constructor
        public Comment(String username, String comment, int puan) {
            this.username = username;
            this.comment = comment;
            this.puan = puan;
        }

        // Getter ve Setter'lar
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getPuan() {
            return puan;
        }

        public void setPuan(int puan) {
            this.puan = puan;
        }
    }

    
    private String hesapId; // Satıcının hesap ID'si

    // Parametresiz Constructor (Gerekli!)
    public ProductContract() {
    	
    }
    // Parameteril constructor
    public ProductContract(String id, String name, String description, double price, List<String> photoUrls,int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photoUrls = photoUrls;
        this.stock=stock;

    }


    // Getter ve Setter
    public String getHesapId() {
        return hesapId;
    }

    public void setHesapId(String hesapId) {
        this.hesapId = hesapId;
    } 
    public List<String> getPhotoUrls() {
        return photoUrls;
    }



	public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
	public int getStockadedi() {
		return stock;
	}
	public void setStockadedi(int stock) {
		this.stock = stock;
	}
}
	