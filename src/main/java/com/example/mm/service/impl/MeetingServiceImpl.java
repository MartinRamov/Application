package com.example.mm.service.impl;

import com.example.mm.model.Chat;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.persistence.ChatRepositoryCrud;
import com.example.mm.persistence.MeetingRepositoryCrud;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.ChatService;
import com.example.mm.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Autowired
    private ChatService chatService;

    @Override
    public Meeting createMeeting(String title, ActivityCategory ac, LocalDate date,
                                 LocalTime timeFrom, LocalTime timeTo) {
        Meeting meeting = new Meeting();
        meeting.title = title;
        meeting.activityCategory = ac;
        meeting.date = date;
        meeting.timeFrom = timeFrom;
        meeting.timeTo = timeTo;
        Chat chat=chatService.createChat();
        meeting.chat=chat;
        return meetingRepositoryCrud.save(meeting);
    }

    @Override
    public Meeting updateMeeting(Long meeting_id, String title, ActivityCategory ac, LocalDate date,
                                 LocalTime timeFrom, LocalTime timeTo) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        if(title!=null)
        meeting.title = title;
        if(ac!=null)
        meeting.activityCategory = ac;
        if(date!=null)
        meeting.date = date;
        if(timeFrom!=null)
        meeting.timeFrom = timeFrom;
        if(timeTo!=null)
        meeting.timeTo = timeTo;
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
        return u.meetings;
    }

    @Override
    public Set<User> getUsersInMeeting(Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        return meeting.users;
    }

    @Override
    public Set<User> getActiveUsersInMeeting(Long meeting_id) {
        Meeting meeting=meetingRepositoryCrud.findOne(meeting_id);
        return meeting.chat.users;
    }

    @Override
    public void accept(Long user_id, Long meeting_id) {
        Meeting meeting=meetingRepositoryCrud.findOne(meeting_id);
        Chat chat=meeting.chat;
        chatService.addUser(chat.id,user_id);
    }

    @Override
    public void decline(Long user_id, Long meeting_id) {

    }

    @Override
    public void addUserToMeeting(Long user_id, Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        User user = userRepositoryCrud.findOne(user_id);
        if (!meeting.users.contains(user) && !user.meetings.contains(meeting)) {
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
        if (meeting.users.contains(user) && user.meetings.contains(meeting)) {
            meeting.users.remove(user);
            user.meetings.remove(meeting);
            meetingRepositoryCrud.save(meeting);
            userRepositoryCrud.save(user);
        }
    }

    @Override
    public Integer getNumberOfUsersInMeeting(Long meeting_id) {
        Meeting meeting = meetingRepositoryCrud.findOne(meeting_id);
        return meeting.users.size();
    }
}
