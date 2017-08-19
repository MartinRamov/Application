package com.example.mm.service;

import javax.mail.MessagingException;

public interface MailSendingService {

    void sendEmail(String firstName, String lastName, String email, String from, String url) throws MessagingException;
}
