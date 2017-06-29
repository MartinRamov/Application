package com.example.mm.service;

import com.example.mm.model.Meeting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
        Meeting meeting = meetingService.createMeeting();
        Assert.assertNotNull("Meeting is not created", meeting);
        meetingService.deleteMeeting(meeting.id);
        Meeting m = meetingService.getMeeting(meeting.id);
        Assert.assertNull("Meeting is not deleted", m);
    }

    @Test
    public void addOrDeleteUsersInMeeting() {
        Meeting meeting = meetingService.createMeeting();
        meetingService.addUserToMeeting(1l, meeting.id);
        Integer count = meetingService.getNumberOfUsersInMeeting(meeting.id);
        Assert.assertEquals("User is not added to meeting", new Integer(1), count);
        meetingService.removeUserFromMeeting(1l, meeting.id);
        count = meetingService.getNumberOfUsersInMeeting(meeting.id);
        Assert.assertEquals("User is not removed from meeting", new Integer(0), count);
    }
}
