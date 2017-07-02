package com.example.mm.service;

import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.persistence.UserRepositoryCrud;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Created by Martin on 29-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingServiceTest {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    private User user;

    @Before
    public void prepare() {
        user = new User();
        user.firstName = "User";
        user.lastName = "Last name";
        user.email = "bb@bb.com";
        user = userRepositoryCrud.save(user);
    }

    @Test
    public void crudTest(){
        Meeting meeting = meetingService.createMeeting("Meeting_Title",
                ActivityCategory.SPORT, LocalDate.now(),
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(4));
        Assert.assertNotNull("Meeting is not created", meeting);
        meetingService.deleteMeeting(meeting.id);
        Meeting m = meetingService.getMeeting(meeting.id);
        Assert.assertNull("Meeting is not deleted", m);
    }

    @Test
    public void addOrDeleteUsersInMeeting() {
        Meeting meeting = meetingService.createMeeting("Meeting_Title",
                ActivityCategory.SPORT, LocalDate.now(),
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(4));
        meetingService.addUserToMeeting(user.id, meeting.id);
        Integer count = meetingService.getNumberOfUsersInMeeting(meeting.id);
        Assert.assertEquals("User is not added to meeting", new Integer(1), count);
        System.out.println(meetingService.getMeetingsForUser(user.id));
        System.out.println(meetingService.getUsersInMeeting(meeting.id));
        meetingService.removeUserFromMeeting(user.id, meeting.id);
        count = meetingService.getNumberOfUsersInMeeting(meeting.id);
        Assert.assertEquals("User is not removed from meeting", new Integer(0), count);
    }
}
