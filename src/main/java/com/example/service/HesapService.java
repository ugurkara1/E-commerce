package com.example.service;

import com.example.dto.HesapDto;
import com.example.dto.SaticiHesapDto;
import com.example.model.HesapContract;
import com.example.model.MusteriContract;
import com.example.model.SaticiContract;
import com.example.repository.HesapRepository;
import com.example.repository.MusteriRepository;
import com.example.repository.SaticiRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class HesapService {
    @Autowired
    private MusteriRepository musteriRepository;
    @Autowired
    private HesapRepository hesapRepository;
    @Autowired
    private SaticiRepository saticiRepository;

    @Autowired
    private MailService emailService; // Email servisini autowire ettik.
    
    private Map<String, String> verificationCodes = new HashMap<>();
    
    
    // Yeni müşteri kaydı oluşturma
    public ResponseEntity<String> kayitOl(HesapDto hesapDto) {
        // Email ve telefon numarası ile kayıt kontrolü
        Optional<MusteriContract> existingEmailRecord = musteriRepository.findByEmail(hesapDto.getEmail());
        Optional<MusteriContract> existingPhoneRecord = musteriRepository.findByTelefon(hesapDto.getTelefon());

        if (existingEmailRecord.isPresent() || existingPhoneRecord.isPresent()) {
            return ResponseEntity.badRequest().body("Bu email veya telefon numarası zaten kayıtlı.");
        }

        // Şifre hashleme
        String sifreHash = BCrypt.hashpw(hesapDto.getSifre(), BCrypt.gensalt());

        // MusteriContract nesnesi oluşturma ve doldurma
        MusteriContract musteri = new MusteriContract();
        musteri.setEmail(hesapDto.getEmail());
        musteri.setSifre(sifreHash);
        musteri.setTelefon(hesapDto.getTelefon());
        musteri.setMusteriAdiSoyadi(hesapDto.getMusteriAdiSoyadi());
        musteri.setCinsiyet(hesapDto.getCinsiyet());
        musteri.setAdres(hesapDto.getAdres());

        if (hesapDto.getBoy() != null) musteri.setBoy(hesapDto.getBoy());
        if (hesapDto.getKilo() != null) musteri.setKilo(hesapDto.getKilo());

        // Doğrulama kodunu oluşturma ve gönderme
        String verificationCode = emailService.generateVerificationCode();
        emailService.sendVerificationEmail(hesapDto.getEmail(), verificationCode);
        verificationCodes.put(hesapDto.getEmail(), verificationCode);

        // Müşteriyi veritabanına kaydetme
        musteriRepository.save(musteri);
        return ResponseEntity.ok("Üyelik başarılı. Lütfen e-posta adresinize gönderilen doğrulama kodunu giriniz.");
    }

 // Doğrulama kodunu doğrulama metodu
    public ResponseEntity<String> dogrulamaKodunuDogrula(String email, String girilenKod) {
        String actualCode = verificationCodes.get(email);

        if (actualCode != null && actualCode.equals(girilenKod)) {
            verificationCodes.remove(email); // Kod doğrulandıktan sonra kaldırılıyor
            return ResponseEntity.ok("Doğrulama başarılı. Üyeliğiniz onaylandı.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Geçersiz doğrulama kodu.");
        }
    }

    public ResponseEntity<String> SaticikayitOl(SaticiHesapDto saticihesapDto) {
        // Email ve telefon numarası ile kayıt kontrolü
        Optional<SaticiContract> existingEmailRecord = saticiRepository.findByEmail(saticihesapDto.getEmail());
        Optional<SaticiContract> existingPhoneRecord = saticiRepository.findByTelefon(saticihesapDto.getTelefon());
        Optional<SaticiContract> existingMarkaRecords=saticiRepository.findByMagazaAdi(saticihesapDto.getMagazaAdi());
        if (existingEmailRecord.isPresent() || existingPhoneRecord.isPresent() || existingMarkaRecords.isPresent()) {
            return ResponseEntity.badRequest().body("Bu email,telefon numarası veya marka zaten kayıtlı.");
        }

        // Şifre hashleme
        String sifreHash = BCrypt.hashpw(saticihesapDto.getSifre(), BCrypt.gensalt());

        // SaticiContract nesnesi oluşturma
        SaticiContract satici = new SaticiContract();
        satici.setEmail(saticihesapDto.getEmail());
        satici.setSifre(sifreHash);
        satici.setTelefon(saticihesapDto.getTelefon());
        satici.setSaticiAdSoyad(saticihesapDto.getSaticiAdSoyad());
        satici.setMagazaAdi(saticihesapDto.getMagazaAdi());
        satici.setAdres(saticihesapDto.getAdres());


        //String verificationCode = email.generateVerificationCode();
        //email.sendVerificationEmail(hesapDto.getEmail(), verificationCode);
        //verificationCodes.put(hesapDto.getEmail(), verificationCode); // Doğrulama kodunu kaydediyoruz
        // Doğrulama kodunu oluşturma ve gönderme
        String verificationCode = emailService.generateVerificationCode();
        emailService.sendVerificationEmail(saticihesapDto.getEmail(), verificationCode);
        verificationCodes.put(saticihesapDto.getEmail(), verificationCode);
        // MusteriContract nesnesini veritabanına kaydetme
        saticiRepository.save(satici);
        return ResponseEntity.ok("Üyelik başarılı. ");
    }
    

    // Kullanıcı doğrulama kodlarını geçici olarak saklamak için bir map

    


    

    // Şifre doğrulama metodu
    public boolean sifreDogrula(String girilenSifre, String kayitliSifreHash) {
        return BCrypt.checkpw(girilenSifre, kayitliSifreHash);
    }

    // Tüm hesapları listeleme
    public List<HesapContract> getAllHesaplar() {
        return hesapRepository.findAll();
    }

    // ID'ye göre hesap bulma
    public Optional<HesapContract> getHesapById(String hesapId) {
        return hesapRepository.findById(hesapId);
    }

    // Email ile hesap bulma
    public Optional<HesapContract> getHesapByEmail(String email) {
        return hesapRepository.findByEmail(email);
    }

    // Hesap güncelleme
    public HesapContract updateHesap(String hesapId, HesapContract hesap) {
        return hesapRepository.findById(hesapId)
                .map(existingHesap -> {
                    if (StringUtils.hasText(hesap.getEmail())) {
                        existingHesap.setEmail(hesap.getEmail());
                    }
                    if (StringUtils.hasText(hesap.getSifre())) {
                        String sifreHash = BCrypt.hashpw(hesap.getSifre(), BCrypt.gensalt());
                        existingHesap.setSifre(sifreHash);
                    }
                    if (StringUtils.hasText(hesap.getTelefon())) {
                        existingHesap.setTelefon(hesap.getTelefon());
                    }
                    return hesapRepository.save(existingHesap);
                })
                .orElseThrow(() -> new IllegalArgumentException("Hesap bulunamadı."));
    }

    // Hesap silme
    public void deleteHesap(String hesapId) {
        hesapRepository.deleteById(hesapId);
    }
}