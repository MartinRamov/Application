package com.example.mm.service;

import com.example.mm.model.Activity;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;
import com.example.mm.persistence.crud.UserRepositoryCrud;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by Martin on 30-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Test
    public void createActivity() {
        Activity activity = activityService.createActivity(1l, "Title",
                ActivityCategory.SPORT, ActivityTime.EVERY_DAY, LocalDate.now(),
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(4));
        Assert.assertNotNull("Activity is not created", activity);
    }

    @Test
    public void deleteActivity() {
        Activity first = activityService.createActivity(1l, "Title",
                ActivityCategory.WORK, ActivityTime.ONLY_ONCE, LocalDate.now(),
                LocalTime.now().plusHours(1), LocalTime.now().plusHours(5));
        activityService.deleteActivity(1l, first.id);
        Activity activity = activityService.getActivityById(first.id);
        Assert.assertNull("Activity is not deleted", activity);
    }

    @Test
    public void getActivitiesForUser() {
        Set<Activity> activities = activityService.getUserActivities(1l);
        Assert.assertNotNull("Error for user activities", activities);
        System.out.println(activities.size() + "\n" + activities);
    }
}
