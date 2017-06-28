package com.example.mm.model;

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

    @ManyToMany
    public List<User> friends= new ArrayList<>();

    @ManyToMany
    public List<Meeting> meetings= new ArrayList<>();

    @OneToMany
    public List<Activity> activities= new ArrayList<>();


}
