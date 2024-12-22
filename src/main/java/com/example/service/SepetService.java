package com.example.service;

import com.example.model.ProductContract;
import com.example.model.SepetContract;
import com.example.repository.ProductRepository;
import com.example.repository.SepetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;  // Bu satır eklendi
import java.util.Optional;

@Service
public class SepetService {

    @Autowired
    private SepetRepository sepetRepository;

    @Autowired
    private ProductRepository productRepository;

    public void sepeteUrunEkle(String musteriId, String productId) throws Exception {
        Optional<ProductContract> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new Exception("Ürün bulunamadı!");
        }

        ProductContract product = optionalProduct.get();

        // Müşteri için sepeti getir veya yeni bir sepet oluştur
        SepetContract sepet = sepetRepository.findById(musteriId)
                .orElse(new SepetContract(musteriId, 0, new ArrayList<>()));

        sepet.getUrunler().add(product);
        sepet.setToplamFiyat(sepet.getUrunler().stream().mapToDouble(ProductContract::getPrice).sum());

        sepetRepository.save(sepet);
    }

    public void sepetteUrunSil(String musteriId, String productId) throws Exception {
        SepetContract sepet = sepetRepository.findById(musteriId)
                .orElseThrow(() -> new Exception("Sepet bulunamadı!"));

        sepet.getUrunler().removeIf(product -> product.getId().equals(productId));
        sepet.setToplamFiyat(sepet.getUrunler().stream().mapToDouble(ProductContract::getPrice).sum());

        sepetRepository.save(sepet);
    }

    public String siparisiTamamla() throws Exception {
        // Sepeti al
        SepetContract sepet = sepetRepository.findById("default_sepet_id")
                .orElseThrow(() -> new Exception("Sepet bulunamadı!"));

        // Sepetteki ürünleri kontrol et veya başka bir işlem yap
        if (sepet.getUrunler().isEmpty()) {
            throw new Exception("Sepetiniz boş, sipariş tamamlanamaz!");
        }

        // Sepet içeriğini işleyin (örneğin, veritabanına kaydedin veya ödeme işlemi başlatın)
        // Sipariş tamamlama işlemi yapılabilir, örneğin ödeme işlemi veya sipariş kaydı yapılabilir.

        // Sepeti temizleyin
        sepet.getUrunler().clear();
        sepet.setToplamFiyat(0); // Toplam fiyatı sıfırlayın

        // Sepeti kaydedin (boşaltılmış haliyle)
        sepetRepository.save(sepet);

        // Sipariş tamamlandı mesajı döndürün
        return "Sipariş başarıyla tamamlandı!";
    }

    public SepetContract getSepet(String musteriId) {
        return sepetRepository.findById(musteriId)
                .orElse(new SepetContract(musteriId, 0, new ArrayList<>()));
    }
} 