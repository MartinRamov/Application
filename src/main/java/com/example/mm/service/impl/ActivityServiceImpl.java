package com.example.mm.service.impl;

import com.example.mm.model.Activity;
import com.example.mm.model.User;
import com.example.mm.persistence.crud.ActivityRepositoryCrud;
import com.example.mm.persistence.crud.UserRepositoryCrud;
import com.example.mm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Martin on 29-Jun-17.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Autowired
    private ActivityRepositoryCrud activityRepositoryCrud;

    @Override
    public Activity createActivity(Long user_id) {
        Activity activity = new Activity();
        User user = userRepositoryCrud.findOne(user_id);
        activity.user = user;
        activity = activityRepositoryCrud.save(activity);
        user.activities.add(activity);
        userRepositoryCrud.save(user);
        return activity;
    }

    @Override
    public Activity updateActivity(Long user_id, Long activity_id) {
        Activity activity = activityRepositoryCrud.findOne(activity_id);

        //Edit activity here

        User user = userRepositoryCrud.findOne(user_id);
        activity.user = user;
        activity = activityRepositoryCrud.save(activity);
        user.activities.add(activity);
        userRepositoryCrud.save(user);
        return activity;
    }

    @Override
    public void deleteActivity(Long user_id, Long activity_id) {
        User user = userRepositoryCrud.findOne(user_id);
        Activity activity = activityRepositoryCrud.findOne(activity_id);
        user.activities.remove(activity);
        userRepositoryCrud.save(user);
        activityRepositoryCrud.delete(activity_id);
    }

    @Override
    public Activity getActivityById(Long activity_id) {
        return activityRepositoryCrud.findOne(activity_id);
    }

    @Override
    public Set<Activity> getUserActivities(Long user_id) {
        User user = userRepositoryCrud.findOne(user_id);
        return user.activities;
    }

    @Override
    public Integer getNumberOfActivitiesForUser(Long user_id) {
        User user = userRepositoryCrud.findOne(user_id);
        return user.activities.size();
    }
}
