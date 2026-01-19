package com.long1dep.myportfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendToAdmin(String subject, String content) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(from);
        mail.setSubject(subject);
        mail.setText(content);

        mailSender.send(mail);
    }
}
