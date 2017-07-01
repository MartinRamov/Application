package com.example.mm.service;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.Meeting;
import com.example.mm.model.Notification;
import com.example.mm.model.User;
import com.example.mm.model.categories.ActivityCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTests {
    @Autowired
    NotificationService notificationService;
    @Autowired
    UserService userService;
    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    MeetingService meetingService;

    User user1;
    User user2;
    User user3;
    FriendRequest fr;
    Meeting meeting;

    @Before
    public void before() {

        user1 = userService.createUser("User1", "User1", "User1", "User1");
        user2 = userService.createUser("User2", "User2", "User2", "User2");
        user3 = userService.createUser("User3", "User3", "User3", "User3");
        fr = friendRequestService.createFriendRequest(user1.id, user2.id);
        meeting = meetingService.createMeeting("Meeting_Title",
                ActivityCategory.SPORT, LocalDate.now(),
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(4));
        meetingService.addUserToMeeting(user1.id, meeting.id);
        meetingService.addUserToMeeting(user2.id, meeting.id);
        meetingService.addUserToMeeting(user3.id, meeting.id);


    }

    @Test
    public void createNotification() {
        Notification n= notificationService.createNotificationForFriendship(fr.id);
        Assert.assertNotNull("Ne e kreirana notifikacija za prijatelstvo",n);
        Set<Notification> n2=notificationService.createNotificationForMeeting(meeting.id);
        Assert.assertNotNull("Ne e kreirana notifikacija za prijatelstvo",n);
        boolean friend=false;
        for(Notification notif : n2){
            if(user1.id==notif.receiver.id){
                friend=true;
                break;
            }
        }
        Assert.assertEquals("Ne e kreirana n za Soodveten user1", true, friend);
        friend=false;
        for(Notification notif : n2){
            if(user2.id==notif.receiver.id){
                friend=true;
                break;
            }
        }
        Assert.assertEquals("Ne e kreirana n za Soodveten user2", true, friend);

        friend=false;
        for(Notification notif : n2){
            if(user3.id==notif.receiver.id){
                friend=true;
                break;
            }
        }
        Assert.assertEquals("Ne e kreirana n za Soodveten user3", true, friend);

        Assert.assertEquals("Get notification",n.id,notificationService.getNotification(n.id).id);



    }
}
