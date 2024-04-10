package com.example.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public void send(String dest, String assunto, String corpo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(dest);
            message.setSubject(assunto);
            message.setText(corpo);

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void sendHTML1(String dest, String assunto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(MimeMessage.RecipientType.TO, dest);
            message.setSubject(assunto);

            String htmlContent = "<h1>Teste de e-mail com HTML </h1>";
            message.setContent(htmlContent, "text/html; charset=utf-8");

            mailSender.send(message);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void sendHtmlTemplate(String dest, String assunto){
        try{
            Context context = new Context();
            context.setVariable("destinatario", dest);
            context.setVariable("mensagem", assunto);

            String corpo = springTemplateEngine.process("email/template", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setSubject(assunto);
            helper.setTo(dest);
            helper.setText(corpo, true); 

             mailSender.send(mimeMessage);
        }

        catch(Exception e){

        }
    }
}
