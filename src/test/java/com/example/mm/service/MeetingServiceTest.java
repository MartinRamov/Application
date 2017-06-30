package com.example.mm.service;

import com.example.mm.model.Meeting;
import com.example.mm.model.categories.ActivityCategory;
import com.example.mm.model.categories.ActivityTime;
import org.junit.Assert;
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
        meetingService.addUserToMeeting(1l, meeting.id);
        Integer count = meetingService.getNumberOfUsersInMeeting(meeting.id);
        Assert.assertEquals("User is not added to meeting", new Integer(1), count);
        System.out.println(meetingService.getMeetingsForUser(1l));
        System.out.println(meetingService.getUsersInMeeting(meeting.id));
        meetingService.removeUserFromMeeting(1l, meeting.id);
        count = meetingService.getNumberOfUsersInMeeting(meeting.id);
        Assert.assertEquals("User is not removed from meeting", new Integer(0), count);
    }
}
