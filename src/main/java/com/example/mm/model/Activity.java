package com.example.mm.model;

import javax.persistence.*;

/**
 * Created by Win8.1 on 28.06.2017.
 */
@Entity
@Table(name = "activities")
public class Activity extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;

}
