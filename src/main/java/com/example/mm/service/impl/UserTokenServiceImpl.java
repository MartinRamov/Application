package com.example.mm.service.impl;

import com.example.mm.model.UserToken;
import com.example.mm.service.UserTokenService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    private Map<String, UserToken> userTokenMap;
    private static Logger logger = LoggerFactory.getLogger(UserTokenServiceImpl.class);

    public UserTokenServiceImpl() {
        this.userTokenMap = new TreeMap<>();
    }

    @Override
    public String generateToken(String userEmail) {
        for (String tokenId : userTokenMap.keySet()) {
            UserToken userToken = userTokenMap.get(tokenId);
            if (userToken.getUserMail().equals(userEmail)) {
                userTokenMap.remove(tokenId);
            }
        }
        String token = RandomStringUtils.randomAlphanumeric(10);
        UserToken userToken = new UserToken();
        userToken.setTokenId(token);
        userToken.setUserMail(userEmail);
        userToken.setIssueTime(ZonedDateTime.now());
        userTokenMap.put(token, userToken);
        logger.info("Created token {} for user {}", token, userEmail);
        return token;
    }

    @Override
    public boolean isTokenExpired(String tokenId) {
        return !userTokenMap.containsKey(tokenId);
    }

    @Override
    public String getUserEmailForToken(String tokenId) {
        return userTokenMap.get(tokenId).getUserMail();
    }

    @Override
    public void deleteToken(String tokenId) {
        userTokenMap.remove(tokenId);
    }

    @Override
    @Scheduled(fixedDelay = 60000)
    public void deleteExpiredTokens() {
        for (String tokenId : userTokenMap.keySet()) {
            UserToken token = userTokenMap.get(tokenId);
            if (token.getIssueTime().isBefore(ZonedDateTime.now().minusMinutes(15))) {
                userTokenMap.remove(tokenId);
            }
        }
    }
}
