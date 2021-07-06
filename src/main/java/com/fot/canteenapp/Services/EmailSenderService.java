package com.fot.canteenapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        System.out.println("message" + message);

        message.setFrom("skemail95@gmail.com");
        message.setTo("ruvindumadushanka@gmail.com");
        message.setText("Test");
        message.setSubject("hello");

        mailSender.send(message);
        System.out.println("Mail Send...");
    }


}
