package com.example.mm.model;

import com.example.mm.model.categories.NotificationCategory;

import javax.persistence.*;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User receiver;

    @Enumerated(EnumType.STRING)
    public NotificationCategory category;

    @OneToOne
    @JoinColumn(name = "meeting_id")
    public Meeting meeting;

    @OneToOne
    @JoinColumn(name = "friendRequest_id")
    public FriendRequest friendRequest;

    @Column
    public boolean checked;


}
