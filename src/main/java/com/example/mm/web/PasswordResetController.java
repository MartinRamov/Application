package com.example.mm.web;

import com.example.mm.model.User;
import com.example.mm.service.MailSendingService;
import com.example.mm.service.UserService;
import com.example.mm.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/resetPassword", produces = "application/json")
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private MailSendingService mailSendingService;

    private static Logger logger = LoggerFactory.getLogger(PasswordResetController.class);

    @RequestMapping(value = "/createToken", method = RequestMethod.POST)
    public String createToken(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        String result;
        if (user != null && user.password != null) {
            String token = userTokenService.generateToken(user.email);
            String url = "http://localhost:8000/#/resetPassword?token=" + token;
            try {
                logger.info("Sending email to {}", user.email);
                mailSendingService.sendEmail(user.firstName, user.lastName, user.email, "", url);
                result = "OK";
            } catch (MessagingException e) {
                logger.error("Failed sending email to {}", user.email);
                logger.error(e.getMessage());
                result = "error";
            }
        } else if (user == null) {
            result = "mailError";
        } else {
            result = "facebookAccount";
        }
        return "{\"result\" : \"" + result + "\"}";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public boolean resetPassword(@RequestParam String token, @RequestParam String password) {
        logger.info("Resetting password for token {}", token);
        boolean isTokenExpired = userTokenService.isTokenExpired(token);
        if (!isTokenExpired) {
            String email = userTokenService.getUserEmailForToken(token);
            userTokenService.deleteToken(token);
            User user = userService.getUserByEmail(email);
            user = userService.updatePassword(user.id, password);
            return user != null;
        }
        return false;
    }
}
