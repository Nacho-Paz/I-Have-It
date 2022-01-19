/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecodecorner.IHaveIt.services;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author Usuario
 */
//public class EmailServiceImpl implements CommandLineRunner {
//
////    @Autowired
////    private JavaMailSender javaMailSender;
////
////    @Override
////    public void run(String... args) throws Exception {
////        SimpleMailMessage message = new SimpleMailMessage();
////
////        String bodyMessage = "Este es una prueba piloto" + UUID.randomUUID().toString();
////
////        message.setTo("ignaciojavierpazbffs@gmail.com");
////        message.setFrom("thecodecorner.tcc@gmail.com");
////        message.setSubject("Mensaje PRUEBA");
////        message.setText(bodyMessage);
////
////        javaMailSender.send(message);
////    }
//
//}
