package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Entity
@Table(name = "friendRequests")
public class FriendRequest extends BaseEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSender_id")
    public User sender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userReceiver_id")
    public User receiver;

}
