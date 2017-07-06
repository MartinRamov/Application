package com.example.mm.web;

import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by mila.gjurova on 7/6/2017.
 */
@RestController
@RequestMapping(value = "/meetings", produces = "application/json")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Meeting createMeeting(@RequestParam String title, @RequestParam ActivityCategory ac, @RequestParam LocalDate date, @RequestParam
            LocalTime timeFrom, @RequestParam LocalTime timeTo) {
        return meetingService.createMeeting(title, ac, date, timeFrom, timeTo);
    }

    @RequestMapping(value = "/update/{meetingId}", method = RequestMethod.PUT)
    public Meeting updateMeeting(@PathVariable Long meetingId, @RequestParam String title, @RequestParam ActivityCategory ac, @RequestParam LocalDate date,
                                 @RequestParam LocalTime timeFrom, @RequestParam LocalTime timeTo) {
        return meetingService.updateMeeting(meetingId, title, ac, date, timeFrom, timeTo);
    }

    @RequestMapping(value = "/delete/{meetingId}", method = RequestMethod.DELETE)
    public void deleteMeeting(@PathVariable Long meetingId) {
        meetingService.deleteMeeting(meetingId);
    }

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

    @RequestMapping(value = "/accept/{meetingId}", method = RequestMethod.PUT)
    public void acceptMeeting(@PathVariable Long meetingId, @RequestParam Long userId) {
        meetingService.accept(userId, meetingId);
    }

    @RequestMapping(value = "/decline/{meetingId}", method = RequestMethod.PUT)
    public void declineMeeting(@PathVariable Long meetingId, @RequestParam Long userId) {
        meetingService.decline(userId, meetingId);
    }

    @RequestMapping(value = "/addUser/{meetingId}/{userId}", method = RequestMethod.PUT)
    public void addUserToMeeting(@PathVariable Long meetingId, @PathVariable Long userId) {
        meetingService.addUserToMeeting(userId, meetingId);
    }

    @RequestMapping(value = "/removeUser/{meetingId}/{userId}", method = RequestMethod.PUT)
    public void removeUserToMeeting(@PathVariable Long meetingId, @PathVariable Long userId) {
        meetingService.removeUserFromMeeting(userId, meetingId);
    }

    @RequestMapping(value = "/numberOfUsers/{meeting_id}", method = RequestMethod.GET)
    public Integer getNumberOfUsersInMeeting(@PathVariable Long meeting_id) {
        return meetingService.getNumberOfUsersInMeeting(meeting_id);
    }
}
