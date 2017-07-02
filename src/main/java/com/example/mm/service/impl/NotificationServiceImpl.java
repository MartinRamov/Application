package com.example.mm.service.impl;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.Meeting;
import com.example.mm.model.Notification;
import com.example.mm.model.User;
import com.example.mm.model.categories.NotificationCategory;
import com.example.mm.persistence.FriendRequestRepositoryCrud;
import com.example.mm.persistence.MeetingRepositoryCrud;
import com.example.mm.persistence.NotificationRepositoryCrud;
import com.example.mm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    FriendRequestRepositoryCrud friendRequestRepositoryCrud;
    @Autowired
    MeetingRepositoryCrud meetingRepositoryCrud;
    @Autowired
    NotificationRepositoryCrud notificationRepositoryCrud;


    @Override
    public Notification createNotificationForFriendship(Long friendRequestId) {
        FriendRequest friendRequest = friendRequestRepositoryCrud.findOne(friendRequestId);
        Notification notification = new Notification();
        notification.category = NotificationCategory.FriendRequest;
        notification.receiver = friendRequest.receiver;
        notification.checked=false;
        notification.friendRequest=friendRequest;
        notificationRepositoryCrud.save(notification);

        return notification;
    }

    @Override
    public Set<Notification> createNotificationForMeeting(Long meetingId) {
        Meeting meeting = meetingRepositoryCrud.findOne(meetingId);
        Set<User> users = meeting.users;
        Set<Notification> notifications = new HashSet<Notification>();
        for (User u : users) {
            Notification notification = new Notification();
            notification.category = NotificationCategory.Meeting;
            notification.meeting = meeting;
            notification.receiver = u;
            notification.checked=false;
            notificationRepositoryCrud.save(notification);
            notifications.add(notification);
        }

        return notifications;
    }

    @Override
    public Set<Notification> getAll(Long recieverId) {
        Set<Notification> receiversNotifications=new HashSet<>();
        Iterable<Notification> notifications = notificationRepositoryCrud.findAll();
        Iterator<Notification> all = notifications.iterator();
        while (all.hasNext()){
            Notification notification=all.next();
            if (notification.receiver.id == recieverId)
                receiversNotifications.add(notification);

        }

        return receiversNotifications;
    }

    @Override
    public void deleteNotification(Long notificationId) {
         notificationRepositoryCrud.delete(notificationId);

    }

    @Override
    public Notification checkedNotification(Long notificationId) {
        Notification notification=notificationRepositoryCrud.findOne(notificationId);
        notification.checked=true;
        notificationRepositoryCrud.save(notification);
        return notification;
    }

    @Override
    public Set<Notification> getFriendRequests(Long recieverId) {
        Set<Notification> receiversNotifications=new HashSet<>();
        Iterable<Notification> notifications = notificationRepositoryCrud.findAll();
        Iterator<Notification> all = notifications.iterator();
        while (all.hasNext()){
            Notification notification=all.next();
            if (notification.receiver.id == recieverId && notification.friendRequest!=null)
                receiversNotifications.add(notification);

        }

        return receiversNotifications;
    }

    @Override
    public Set<Notification> getMeetings(Long recieverId) {
        Set<Notification> receiversNotifications=new HashSet<>();
        Iterable<Notification> notifications = notificationRepositoryCrud.findAll();
        Iterator<Notification> all = notifications.iterator();
        while (all.hasNext()){
            Notification notification=all.next();
            if (notification.receiver.id == recieverId && notification.meeting!=null)
                receiversNotifications.add(notification);

        }

        return receiversNotifications;
    }

    @Override
    public Notification getNotification(Long notificationId) {
        return notificationRepositoryCrud.findOne(notificationId);
    }
}
