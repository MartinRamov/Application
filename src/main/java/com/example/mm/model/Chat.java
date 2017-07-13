package com.example.mm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "chats")
    public Set<User> users = new TreeSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat", cascade = CascadeType.ALL)
    public Set<ChatItem> chatItems = new TreeSet<>();

}
