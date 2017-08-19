package com.example.mm.service.impl;

import com.example.mm.service.MailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailSendingServiceImpl implements MailSendingService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String firstName, String lastName, String email, String from, String url) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setFrom(from);
        helper.setSentDate(new Date());
        helper.setSubject("Password reset");
        helper.setReplyTo(from);
        helper.setText(String.format("Dear %s %s,\n\nFollow the link below to reset your password:\n%s" +
                "\n\nRegards,\nMMApplication Team", firstName, lastName, url));
        mailSender.send(message);
    }
}
