package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<User> friends = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_meetings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id"))
    public Set<Meeting> meetings = new TreeSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Set<Activity> activities = new TreeSet<>();

    @Override
    public String toString() {
        return String.format("%d. %s %s %s", id, firstname, lastname, email);
    }
}
