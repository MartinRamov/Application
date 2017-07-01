package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "chats")
    public Set<User> users = new TreeSet<User>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat")
    public Set<ChatItem> chatItems = new TreeSet<ChatItem>();


}
