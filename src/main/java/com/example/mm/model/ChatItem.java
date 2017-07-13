package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;

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
    public LocalTime timeSent;

    @Column
    public String message;

}
