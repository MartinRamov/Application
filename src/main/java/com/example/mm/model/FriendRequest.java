package com.example.mm.model;

import javax.persistence.*;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Entity
@Table(name = "friendRequests")
public class FriendRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSender_id")
    public User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userReceiver_id")
    public User receiver;

}
