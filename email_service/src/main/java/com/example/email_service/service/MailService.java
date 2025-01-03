package com.example.email_service.service;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.email_service.models.NotificationEmail;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
    
    private final static Logger LOGGER = LoggerFactory
        .getLogger(MailService.class);
    
    private final JavaMailSender mailSender;

    /**
     * 
     * @param email
     */
    @Async
    void sendMail(NotificationEmail email) { 
        System.out.println("\n✅ MailService.sendMail()");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            var messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom("blog-app@gmail.com");
                messageHelper.setTo(email.getRecipient());
                messageHelper.setSubject(email.getSubject());
                messageHelper.setText(email.getBody());
        };
        try {
            LOGGER.error("Sending verification email!");
            System.out.println("\n✅ Sending over Email!");
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("❌❌Unable to send verification mail!");
        } 
    }
}
