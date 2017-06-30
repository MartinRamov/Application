package com.example.mm.service;

import com.example.mm.model.Activity;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 29-Jun-17.
 */
public interface ActivityService {

    Activity createActivity(Long user_id, String title, ActivityCategory ac,
                            ActivityTime at, LocalDate date,
                            LocalTime timeFrom, LocalTime timeTo);

    Activity updateActivity(Long user_id, Long activity_id, String title,
                            ActivityCategory ac, ActivityTime at,
                            LocalDate date, LocalTime timeFrom,
                            LocalTime timeTo);

    void deleteActivity(Long user_id, Long activity_id);

    Activity getActivityById(Long activity_id);

    Set<Activity> getUserActivities(Long user_id);

    Integer getNumberOfActivitiesForUser(Long user_id);
}
