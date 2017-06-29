package com.example.mm.service;

import com.example.mm.model.Activity;

import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 29-Jun-17.
 */
public interface ActivityService {

    Activity createActivity(Long user_id);

    Activity updateActivity(Long user_id, Long activity_id);

    void deleteActivity(Long user_id, Long activity_id);

    Activity getActivityById(Long activity_id);

    Set<Activity> getUserActivities(Long user_id);

    Integer getNumberOfActivitiesForUser(Long user_id);
}
