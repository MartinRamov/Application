package com.example.mm.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Martin on 28-Jun-17.
 */
@Entity
@Table(name = "meetings")
public class Meeting extends BaseEntity {

    @ManyToMany(mappedBy = "meetings", fetch = FetchType.EAGER)
    public Set<User> users = new TreeSet<>();
}
