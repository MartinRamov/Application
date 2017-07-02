package com.example.mm.model;

import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by Win8.1 on 28.06.2017.
 */
@Entity
@Table(name = "activities")
public class Activity extends BaseEntity {

    @Column
    public String title;

    @Enumerated(EnumType.STRING)
    public ActivityCategory activityCategory;

    @Enumerated(EnumType.STRING)
    public ActivityTime activityTime;

    @Column
    public LocalDate date;

    @Column
    public LocalTime timeFrom;

    @Column
    public LocalTime timeTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;

    @Override
    public String toString() {
        return String.format("%d, %s %s, (%s - %s)", id, title, date, timeFrom, timeTo);
    }

}
