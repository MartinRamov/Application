package com.example.mm.web;

import com.example.mm.model.Activity;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;
import com.example.mm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by mila.gjurova on 7/5/2017.
 */
@RestController
@RequestMapping(value = "/activities", produces = "application/json")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public Set<Activity> getAllActivitiesForUser(@PathVariable Long id) {
        return activityService.getUserActivities(id);
    }

    @RequestMapping(value = "/delete/{idUser}/{idActivity}", method = RequestMethod.DELETE)
    public void deleteActivity(@PathVariable Long idUser, @PathVariable Long idActivity) {
        activityService.deleteActivity(idUser, idActivity);
    }

    @RequestMapping(value = "/getActivity/{id}", method = RequestMethod.GET)
    public Activity getActivity(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Activity createActivity(@RequestParam Long user_id, @RequestParam String title, @RequestParam ActivityCategory ac,
                                   @RequestParam ActivityTime at, @RequestParam LocalDate date,
                                   @RequestParam LocalTime timeFrom, @RequestParam LocalTime timeTo) {
        return activityService.createActivity(user_id, title, ac, at, date, timeFrom, timeTo);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Activity updateActivity(@RequestParam Long user_id, @PathVariable Long id, @RequestParam ActivityCategory ac,
                                   @RequestParam ActivityTime at, @RequestParam LocalDate date,
                                   @RequestParam LocalTime timeFrom, @RequestParam LocalTime timeTo, @RequestParam String title) {
        return activityService.updateActivity(user_id, id, title, ac, at, date, timeFrom, timeTo);
    }

    @RequestMapping(value = "/getNumActivities/{userId}", method = RequestMethod.GET)
    public Integer getNumberOfActivitiesForUser(@PathVariable Long userId){
        return activityService.getNumberOfActivitiesForUser(userId);
    }


}
