package com.example.mm.service;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendRequestServiceTest {

    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private UserService userService;

    private User u1;
    private User u2;

    @Before
    public void createUsers() {
        u1 = userService.createUser("sender", "sender", "sender", "sender");
        u2 = userService.createUser("reciever", "reciever", "reciever", "reciever");
    }


    @Test
    public void testAllFunctions() {
        FriendRequest fr = friendRequestService.createFriendRequest(u1.id, u2.id);
        Assert.assertNotNull("Ne e kreiran friendRequest", fr);
        Assert.assertEquals("Greska sender", u1, friendRequestService.getSender(fr.id));
        Assert.assertEquals("Greska reciever", u2, friendRequestService.getReceiver(fr.id));



        Set<FriendRequest> set = friendRequestService.getReceivedRequests(u2.id);
        boolean friend=false;
        for(FriendRequest s : set){
            if(u1.id.equals(s.sender.id)){
                friend=true;
                break;
            }


        }
        Assert.assertEquals("Greska requests",true,friend);

        FriendRequest fr2=friendRequestService.acceptFriendRequest(fr.id);
        Assert.assertEquals(fr.id,fr2.id);



    }
}
