package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Entity
@Table(name = "chatItems")
public class ChatItem extends BaseEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    public Chat chat;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;

    @Column
    public LocalDateTime timeSent;

    @Column
    public String message;

}
