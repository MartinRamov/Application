package com.example.mm.service;

import com.example.mm.model.Meeting;
import com.example.mm.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 29-Jun-17.
 */
public interface MeetingService {

    Meeting createMeeting();

    Meeting updateMeeting(Long meeting_id);

    void deleteMeeting(Long meeting_id);

    Meeting getMeeting(Long meeting_id);

    Set<Meeting> getMeetingsForUser(Long user_id);

    Set<User> getUsersInMeeting(Long meeting_id);

    Set<User> getActiveUsersInMeeting(Long meeting_id);

    void accept(Long user_id, Long meeting_id);

    void decline(Long user_id, Long meeting_id);

    void addUserToMeeting(Long user_id, Long meeting_id);

    void removeUserFromMeeting(Long user_id, Long meeting_id);

    Integer getNumberOfUsersInMeeting(Long meeting_id);
}
