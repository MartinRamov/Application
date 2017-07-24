package com.example.mm.service;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.Meeting;
import com.example.mm.model.Notification;

import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
public interface NotificationService {

    Notification createNotificationForFriendship(Long friendRequestId);

    Notification createNotificationForMeeting(Long meetingId,Long userId);

    Set<Notification> getAll(Long recieverId);

    void deleteNotification(Long notificationId);

    Notification checkedNotification(Long notificationId);

    Set<Notification> getFriendRequests(Long recieverId);

    Set<Notification> getMeetings(Long recieverId);

    Notification getNotification(Long notificationId);

    void deleteNotificationForFriendship(FriendRequest friendRequest);

    void deleteNotificationForMeeting(Meeting meeting, Long userId);


}
