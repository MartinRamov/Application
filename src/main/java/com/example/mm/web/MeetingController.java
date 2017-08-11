package com.example.mm.web;

import com.example.mm.model.Meeting;
import com.example.mm.model.Notification;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.service.MeetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Meeting Controller
 */
@RestController
@RequestMapping(value = "/meetings", produces = "application/json")
public class MeetingController {

    private static Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    MeetingService meetingService;

    //Tested
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Meeting createMeeting(@RequestParam String title, @RequestParam ActivityCategory ac,
                                 @RequestParam String date, @RequestParam String timeFrom,
                                 @RequestParam String timeTo) {
        logger.info("Creating meeting {} {} {}", date, timeFrom, timeTo);
        String[] dateArray = date.split("-");
        LocalDate dateParsed = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
        String[] timeArrayF = timeFrom.split(":");
        LocalTime from = LocalTime.of(Integer.parseInt(timeArrayF[0]), Integer.parseInt(timeArrayF[1]));
        String[] timeArrayT = timeTo.split(":");
        LocalTime to = LocalTime.of(Integer.parseInt(timeArrayT[0]), Integer.parseInt(timeArrayT[1]));
        return meetingService.createMeeting(title, ac, dateParsed, from, to);
    }

    //Tested
    @RequestMapping(value = "/update/{meetingId}", method = RequestMethod.POST)
    public Meeting updateMeeting(@PathVariable Long meetingId, @RequestParam String title,
                                 @RequestParam ActivityCategory ac, @RequestParam String date,
                                 @RequestParam String timeFrom, @RequestParam String timeTo) {
        logger.info("Updating meeting {} {} {}", date, timeFrom, timeTo);
        String[] dateArray = date.split("-");
        LocalDate dateParsed = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
        String[] timeArrayF = timeFrom.split(":");
        LocalTime from = LocalTime.of(Integer.parseInt(timeArrayF[0]), Integer.parseInt(timeArrayF[1]));
        String[] timeArrayT = timeTo.split(":");
        LocalTime to = LocalTime.of(Integer.parseInt(timeArrayT[0]), Integer.parseInt(timeArrayT[1]));
        return meetingService.updateMeeting(meetingId, title, ac, dateParsed, from, to);
    }

    //Tested , problem like with user
    @RequestMapping(value = "/delete/{meetingId}", method = RequestMethod.DELETE)
    public void deleteMeeting(@PathVariable Long meetingId) {
        meetingService.deleteMeeting(meetingId);
    }


    //Test
    @RequestMapping(value = "/get/{meetingId}", method = RequestMethod.GET)
    public Meeting getMeeting(@PathVariable Long meetingId) {
        return meetingService.getMeeting(meetingId);
    }

    @RequestMapping(value = "/getMeetings/{userId}", method = RequestMethod.GET)
    public Set<Meeting> getMeetingsForUser(@PathVariable Long userId) {
        return meetingService.getMeetingsForUser(userId);
    }

    @RequestMapping(value = "/getUsers/{meetingId}", method = RequestMethod.GET)
    public Set<User> getUsersInMeeting(@PathVariable Long meetingId) {
        return meetingService.getUsersInMeeting(meetingId);
    }


    @RequestMapping(value = "/getActiveUsers/{meetingId}", method = RequestMethod.GET)
    public Set<User> getActiveUsersInMeeting(@PathVariable Long meetingId) {
        return meetingService.getActiveUsersInMeeting(meetingId);
    }

    //Not implemented
    @RequestMapping(value = "/accept/{meetingId}", method = RequestMethod.POST)
    public void acceptMeeting(@PathVariable Long meetingId, @RequestParam Long userId) {
        meetingService.accept(userId, meetingId);
    }

    @RequestMapping(value = "/decline/{meetingId}", method = RequestMethod.POST)
    public void declineMeeting(@PathVariable Long meetingId, @RequestParam Long userId) {
        meetingService.decline(userId, meetingId);
    }

    //Tested
    @RequestMapping(value = "/addUser/{meetingId}/{userId}", method = RequestMethod.PUT)
    public Notification addUserToMeeting(@PathVariable Long meetingId, @PathVariable Long userId) {
        return meetingService.addUserToMeeting(userId, meetingId);
    }

    //Tested
    @RequestMapping(value = "/removeUser/{meetingId}/{userId}", method = RequestMethod.DELETE)
    public void removeUserFromMeeting(@PathVariable Long meetingId, @PathVariable Long userId) {
        meetingService.removeUserFromMeeting(userId, meetingId);
    }

    //Tested
    @RequestMapping(value = "/numberOfUsers/{meeting_id}", method = RequestMethod.GET)
    public Integer getNumberOfUsersInMeeting(@PathVariable Long meeting_id) {
        return meetingService.getNumberOfUsersInMeeting(meeting_id);
    }
}
