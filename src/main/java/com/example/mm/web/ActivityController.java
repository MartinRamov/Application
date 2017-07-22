package com.example.mm.web;

import com.example.mm.model.Activity;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;
import com.example.mm.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Activity Controller
 */
//Tested
@RestController
@RequestMapping(value = "/activities", produces = "application/json")
public class ActivityController {

    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

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
                                   @RequestParam ActivityTime at, @RequestParam String date,
                                   @RequestParam String timeFrom, @RequestParam String timeTo) {
        logger.info("Creating activity {} {} {}", date, timeFrom, timeTo);
        String[] dateArray = date.split("-");
        LocalDate dateParsed = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]) ,Integer.parseInt(dateArray[2]));
        String[] timeArrayF = timeFrom.split(":");
        LocalTime from = LocalTime.of(Integer.parseInt(timeArrayF[0]), Integer.parseInt(timeArrayF[1]));
        String[] timeArrayT = timeTo.split(":");
        LocalTime to = LocalTime.of(Integer.parseInt(timeArrayT[0]), Integer.parseInt(timeArrayT[1]));
        return activityService.createActivity(user_id, title, ac, at, dateParsed, from, to);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Activity updateActivity(@RequestParam Long user_id, @PathVariable Long id, @RequestParam ActivityCategory ac,
                                   @RequestParam ActivityTime at, @RequestParam String date,
                                   @RequestParam String timeFrom, @RequestParam String timeTo, @RequestParam String title) {
        logger.info("Updating activity {} {} {}", date, timeFrom, timeTo);
        String[] dateArray = date.split("-");
        LocalDate dateParsed = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]) ,Integer.parseInt(dateArray[2]));
        String[] timeArrayF = timeFrom.split(":");
        LocalTime from = LocalTime.of(Integer.parseInt(timeArrayF[0]), Integer.parseInt(timeArrayF[1]));
        String[] timeArrayT = timeTo.split(":");
        LocalTime to = LocalTime.of(Integer.parseInt(timeArrayT[0]), Integer.parseInt(timeArrayT[1]));
        return activityService.updateActivity(user_id, id, title, ac, at, dateParsed, from, to);
    }

    @RequestMapping(value = "/getNumActivities/{userId}", method = RequestMethod.GET)
    public Integer getNumberOfActivitiesForUser(@PathVariable Long userId) {
        return activityService.getNumberOfActivitiesForUser(userId);
    }


}
