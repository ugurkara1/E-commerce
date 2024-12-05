//package com.example.service;
//
//import com.example.dto.UrunDTO;
//import com.example.model.KategoriContract;
//import com.example.model.UrunContract;
//import com.example.repository.KategoriRepository;
//import com.example.repository.UrunRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Optional;
//
//@Service
//public class UrunService {
//
//    private static final String FOTO_PATH = "uploads/";
//
//    @Autowired
//    private UrunRepository urunRepository;
//
//    @Autowired
//    private KategoriRepository kategoriRepository;
//
//    public UrunContract urunEkle(UrunDTO urunDTO) throws IOException {
//        String fotografYolu = null;
//        if (urunDTO.getUrunFotograf() != null && !urunDTO.getUrunFotograf().isEmpty()) {
//            fotografYolu = saveFoto(urunDTO.getUrunFotograf());
//        }
//
//        Optional<KategoriContract> kategoriOpt = kategoriRepository.findByName(urunDTO.getKategoriAdi());
//        if (kategoriOpt.isEmpty()) {
//            throw new IllegalArgumentException("Kategori bulunamadı: " + urunDTO.getKategoriAdi());
//        }
//
//        KategoriContract kategori = kategoriOpt.get();
//        UrunContract urun = new UrunContract();
//        urun.setUrunIsmi(urunDTO.getUrunIsmi());
//        urun.setUrunRenk(urunDTO.getUrunRenk());
//        urun.setUrunFiyat(urunDTO.getUrunFiyat());
//        urun.setGaranti(urunDTO.isGaranti());
//        urun.setUrunAdet(urunDTO.getUrunAdet());
//        urun.setUrunFotograf(fotografYolu);
//        urun.setUrunAciklama(urunDTO.getUrunAciklama());
//        urun.setKategoriAdi(kategori.getName());
//
//        return urunRepository.save(urun);
//    }
//
//    private String saveFoto(MultipartFile file) throws IOException {
//        if (!Files.exists(Paths.get(FOTO_PATH))) {
//            Files.createDirectories(Paths.get(FOTO_PATH));
//        }
//        String filePath = FOTO_PATH + file.getOriginalFilename();
//        file.transferTo(new File(filePath));
//        return filePath;
//    }
//}
