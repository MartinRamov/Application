package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win8.1 on 28.06.2017.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column
    public String firstname;

    @Column
    public String lastname;

    @Column
    public String email;

    @Column
    public String password;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    public List<User> friends = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_meetings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id"))
    public List<Meeting> meetings = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    public List<Activity> activities = new ArrayList<>();


}
