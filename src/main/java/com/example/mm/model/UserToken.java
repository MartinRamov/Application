package com.example.mm.model;

import java.time.ZonedDateTime;

public class UserToken {

    private String tokenId;
    private String userMail;
    private ZonedDateTime issueTime;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public ZonedDateTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(ZonedDateTime issueTime) {
        this.issueTime = issueTime;
    }

    @Override
    public String toString() {
        return String.format("Token: %s\nUser: %s\nIssued at: %s", tokenId, userMail, issueTime);
    }
}
