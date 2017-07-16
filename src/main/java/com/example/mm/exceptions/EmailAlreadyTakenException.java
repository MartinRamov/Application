package com.example.mm.exceptions;


/**
 * Created by Martin on 16-Jul-17.
 */
public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException(String email) {
        super("Email " + email + " is already taken.");
    }
}
