package com.example.mm.model;

import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Martin on 28-Jun-17.
 */
@Entity
@Table(name = "meetings")
public class Meeting extends BaseEntity {

    @Column
    public String title;

    @Enumerated(EnumType.STRING)
    public ActivityCategory activityCategory;

    @Column
    public LocalDate date;

    @Column
    public LocalTime timeFrom;

    @Column
    public LocalTime timeTo;

    @ManyToMany(mappedBy = "meetings", fetch = FetchType.EAGER)
    public Set<User> users = new TreeSet<>();

    @Override
    public String toString() {
        return String.format("%d, %s %s, (%s - %s)", id, title, date, timeFrom, timeTo);
    }
}
