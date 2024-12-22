package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.KartContract;
import com.example.repository.KartRepository;

@Service
public class KartService {

    @Autowired
    private KartRepository kartRepository;

    // Kart bilgilerini doğrulayan ve ödeme yapan bir metot
    public KartContract kartlaOdemeYap(String kartNo, String sifre, String cvv) throws Exception {
        // Kart doğrulama işlemi yapılacak
        KartContract kart = kartRepository.findByKartNo(kartNo)
                .orElseThrow(() -> new Exception("Kart bulunamadı"));
        
        if (!kart.getSifre().equals(sifre)) {
            throw new Exception("Şifre yanlış");
        }
        
        // CVV kontrolü yapılabilir
        if (!kart.getCvv().equals(cvv)) {
            throw new Exception("CVV yanlış");
        }

        // Para miktarını kontrol et
        if (kart.getParaMiktari() <= 0) {
            throw new Exception("Yetersiz bakiye");
        }

        // Ödeme işlemi başarılı
        return kart;
    }
}
