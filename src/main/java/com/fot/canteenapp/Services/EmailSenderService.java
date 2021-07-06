package com.fot.canteenapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to,String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("skemail95@gmail.com");
        message.setTo(to);
        message.setText(body);
        message.setSubject("Your order has been received");

        mailSender.send(message);
        System.out.println("Mail Send...");
    }


}
