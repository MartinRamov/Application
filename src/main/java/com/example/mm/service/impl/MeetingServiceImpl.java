package com.example.mm.service.impl;

import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.persistence.crud.MeetingRepositoryCrud;
import com.example.mm.persistence.crud.UserRepositoryCrud;
import com.example.mm.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 29-Jun-17.
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepositoryCrud meetingRepositoryCrud;

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Override
    public Meeting createMeeting() {
        Meeting meeting = new Meeting();
        return meetingRepositoryCrud.save(meeting);
    }

    @Override
    public Meeting updateMeeting(Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        //Do something with meeting
        return meetingRepositoryCrud.save(meeting);
    }

    @Override
    public void deleteMeeting(Long meeting_id) {
        meetingRepositoryCrud.delete(meeting_id);
    }

    @Override
    public Meeting getMeeting(Long meeting_id) {
        return meetingRepositoryCrud.findOne(meeting_id);
    }

    @Override
    public Set<Meeting> getMeetingsForUser(Long user_id) {
        User u = userRepositoryCrud.findOne(user_id);
        Set<Meeting> meetings = u.meetings;
        return meetings;
    }

    @Override
    public Set<User> getUsersInMeeting(Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        Set<User> users = meeting.users;
        return users;
    }

    @Override
    public Set<User> getActiveUsersInMeeting(Long meeting_id) {
        return null;
    }

    @Override
    public void accept(Long user_id, Long meeting_id) {

    }

    @Override
    public void decline(Long user_id, Long meeting_id) {

    }

    @Override
    public void addUserToMeeting(Long user_id, Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        User user = userRepositoryCrud.findOne(user_id);
        if(!meeting.users.contains(user) && !user.meetings.contains(meeting)) {
            meeting.users.add(user);
            user.meetings.add(meeting);
            meetingRepositoryCrud.save(meeting);
            userRepositoryCrud.save(user);
        }
    }

    @Override
    public void removeUserFromMeeting(Long user_id, Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        User user = userRepositoryCrud.findOne(user_id);
        if(meeting.users.contains(user) && user.meetings.contains(meeting)) {
            meeting.users.remove(user);
            user.meetings.remove(meeting);
            meetingRepositoryCrud.save(meeting);
            userRepositoryCrud.save(user);
        }
    }

    @Override
    public Integer getNumberOfUsersInMeeting(Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        return meeting.users != null ? meeting.users.size() : 1000;
    }
}
