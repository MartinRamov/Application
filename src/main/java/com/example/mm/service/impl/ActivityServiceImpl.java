package com.example.mm.service.impl;

import com.example.mm.model.Activity;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;
import com.example.mm.persistence.ActivityRepositoryCrud;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public Activity createActivity(Long user_id, String title, ActivityCategory ac,
                                   ActivityTime at, LocalDate date,
                                   LocalTime timeFrom, LocalTime timeTo) {
        Activity activity = new Activity();
        activity.title = title;
        activity.activityCategory = ac;
        activity.activityTime = at;
        activity.date = date;
        activity.timeFrom = timeFrom;
        activity.timeTo = timeTo;
        User user = userRepositoryCrud.findOne(user_id);
        activity.user = user;
        activity = activityRepositoryCrud.save(activity);
        user.activities.add(activity);
        userRepositoryCrud.save(user);
        return activity;
    }

    @Override
    public Activity updateActivity(Long user_id, Long activity_id, String title,
                                   ActivityCategory ac, ActivityTime at,
                                   LocalDate date, LocalTime timeFrom,
                                   LocalTime timeTo) {
        Activity activity = activityRepositoryCrud.findOne(activity_id);
        User user = activity.user;
        if(title!=null)
        activity.title = title;
        if(ac!=null)
        activity.activityCategory = ac;
        if(at!=null)
        activity.activityTime = at;
        if(date!=null)
        activity.date = date;
        if(timeFrom!=null)
        activity.timeFrom = timeFrom;
        if(timeTo!=null)
        activity.timeTo = timeTo;
        if(user_id!=null){
            user = userRepositoryCrud.findOne(user_id);
            activity.user = user;
        }

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
        activity.user = null;
        activity = activityRepositoryCrud.save(activity);
        activityRepositoryCrud.delete(activity.id);
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
