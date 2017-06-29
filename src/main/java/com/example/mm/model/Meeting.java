package com.example.mm.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Martin on 28-Jun-17.
 */
@Entity
@Table(name = "meeting")
public class Meeting extends BaseEntity {

    @ManyToMany(mappedBy = "meetings", fetch = FetchType.EAGER)
    public List<User> users;
}
