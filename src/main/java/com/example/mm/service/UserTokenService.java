package com.example.mm.service;

public interface UserTokenService {

    String generateToken(String userEmail);

    boolean isTokenExpired(String tokenId);

    void deleteExpiredTokens();

    String getUserEmailForToken(String tokenId);

    void deleteToken(String tokenId);
}
