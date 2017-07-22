package com.example.mm.web;

import com.example.mm.model.Notification;
import com.example.mm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Notification Controller
 */
@RestController
@RequestMapping(value = "/notifications", produces = "application/json")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/createForFriendRequest/{friendRequestId}", method = RequestMethod.POST)
    public Notification createNotificationForFriendship(@PathVariable Long friendRequestId) {
        return notificationService.createNotificationForFriendship(friendRequestId);
    }

    @RequestMapping(value = "/createForMeeting/{meetingId}", method = RequestMethod.POST)
    public Set<Notification> createNotificationForMeeting(@PathVariable Long meetingId) {
        return notificationService.createNotificationForMeeting(meetingId);
    }


    @RequestMapping(value = "/getAll/{userId}", method = RequestMethod.GET)
    public Set<Notification> getAll(@PathVariable Long userId) {
        return notificationService.getAll(userId);
    }

    @RequestMapping(value = "/delete/{notificationId}", method = RequestMethod.DELETE)
    public void deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    @RequestMapping(value = "/checked/{notificationId}", method = RequestMethod.PUT)
    public Notification checkedNotification(@PathVariable Long notificationId) {
        return notificationService.checkedNotification(notificationId);
    }

    @RequestMapping(value = "/getFriendRequests/{userId}", method = RequestMethod.GET)
    public Set<Notification> getFriendRequests(@PathVariable Long userId) {
        return notificationService.getFriendRequests(userId);
    }

    @RequestMapping(value = "/getMeetings/{userId}", method = RequestMethod.GET)
    public Set<Notification> getMeetings(@PathVariable Long userId) {
        return notificationService.getMeetings(userId);
    }

    @RequestMapping(value = "/get/{notificationId}")
    public Notification getNotification(@PathVariable Long notificationId) {
        return notificationService.getNotification(notificationId);
    }
}
