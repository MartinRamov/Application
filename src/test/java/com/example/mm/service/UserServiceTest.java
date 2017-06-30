package com.example.mm.service;

import com.example.mm.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win8.1 on 30.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        User user = userService.createUser("MilaT", "Gjurova", "mail", "pass");
        Assert.assertNotNull("Ne e kreiran user", user);

    }
    @Test
    public void deleteUser(){
        User user = userService.createUser("MilaT", "Gjurova", "mail", "pass");
        userService.deleteUser(user.id);
        Assert.assertNull("Ne e izbrisan userot", userService.getUserById(user.id));
    }
    @Test
    public void testTwoFunkForFriends(){
        User user = userService.createUser("PrijatelPrv", "Gjurova", "mail", "pass");
        User user2=userService.createUser("Prijatel", "Prijatel", "mail", "pass");

        userService.addFriend(user.id,user2.id);
        Assert.assertEquals("Ne e kreiran friend",true, userService.isFriend(user.id,user2.id));

    }

    @Test
    public void countFriends(){
        Assert.assertEquals("Ne se izbroeni prijatelite", 1,userService.countFriends(Long.parseLong("37")));
        Assert.assertEquals("Ne se izbroeni prijatelite", 0,userService.countFriends(Long.parseLong("35")));
    }



}
