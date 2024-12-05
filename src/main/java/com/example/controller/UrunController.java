//package com.example.controller;
//
//import com.example.model.UrunContract;
//import com.example.repository.UrunRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.util.StringUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/urunler")
//public class UrunController {
//
//    @Autowired
//    private UrunRepository urunRepository;
//
//    private final String UPLOAD_DIR = "uploads/";
//
//    @PostMapping("/urunekle")
//    public UrunContract urunEkle(@RequestParam("urunIsmi") String urunIsmi,
//                                  @RequestParam("urunRenk") String urunRenk,
//                                  @RequestParam("urunFiyat") double urunFiyat,
//                                  @RequestParam("garanti") boolean garanti,
//                                  @RequestParam("urunAdet") int urunAdet,
//                                  @RequestParam("urunFotograf") MultipartFile urunFotograf,
//                                  @RequestParam("urunAciklama") String urunAciklama,
//                                  @RequestParam("kategoriAdi") String kategoriAdi) throws IOException {
//
//        String fotoPath = saveFile(urunFotograf);
//
//        UrunContract urun = new UrunContract();
//        urun.setUrunIsmi(urunIsmi);
//        urun.setUrunRenk(urunRenk);
//        urun.setUrunFiyat(urunFiyat);
//        urun.setGaranti(garanti);
//        urun.setUrunAdet(urunAdet);
//        urun.setUrunFotograf(fotoPath);
//        urun.setUrunAciklama(urunAciklama);
//        urun.setKategoriAdi(kategoriAdi);
//
//        return urunRepository.save(urun);
//    }
//
//    private String saveFile(MultipartFile file) throws IOException {
//        String fileName = UUID.randomUUID().toString() + StringUtils.getFilenameExtension(file.getOriginalFilename());
//        Path path = Paths.get(UPLOAD_DIR + fileName);
//        Files.createDirectories(path.getParent());  // ensure directory exists
//        file.transferTo(path);
//        return path.toString();
//    }
//}
