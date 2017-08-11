package com.example.mm.service;

import com.example.mm.model.Meeting;
import com.example.mm.model.Notification;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 29-Jun-17.
 */
public interface MeetingService {

    Meeting createMeeting(String title, ActivityCategory ac, LocalDate date,
                          LocalTime timeFrom, LocalTime timeTo);

    Meeting updateMeeting(Long meeting_id, String title, ActivityCategory ac, LocalDate date,
                          LocalTime timeFrom, LocalTime timeTo);

    void deleteMeeting(Long meeting_id);

    Meeting getMeeting(Long meeting_id);

    Set<Meeting> getMeetingsForUser(Long user_id);

    Set<User> getUsersInMeeting(Long meeting_id);

    Set<User> getActiveUsersInMeeting(Long meeting_id);

    void accept(Long user_id, Long meeting_id);

    void decline(Long user_id, Long meeting_id);

    Notification addUserToMeeting(Long user_id, Long meeting_id);

    void removeUserFromMeeting(Long user_id, Long meeting_id);

    Integer getNumberOfUsersInMeeting(Long meeting_id);
}
