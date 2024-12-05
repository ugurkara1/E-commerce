package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    // Doğrulama kodu oluşturma
    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6 basamaklı bir kod oluşturur
        return String.valueOf(code);
    }

    // E-posta gönderme
    public void sendVerificationEmail(String toEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("E-posta Doğrulama Kodu");
        message.setText("Doğrulama kodunuz: " + verificationCode);

        try {
            mailSender.send(message);
            
            
        } catch (Exception e) {
            // Hata durumunda loglama yapabilirsiniz
            throw new RuntimeException("E-posta gönderilemedi: " + e.getMessage());
        }
    }

}
