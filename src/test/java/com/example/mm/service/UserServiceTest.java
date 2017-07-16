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
import java.util.Set;

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

        //Count friends test
        User test = userService.createUser("a", "b", "a@a.com", "pas");
        Assert.assertEquals("Conunt frineds error", 0, userService.countFriends(test.id));
        Assert.assertEquals("Conunt frineds error", 1, userService.countFriends(user.id));
        Assert.assertEquals("Conunt frineds error", 1, userService.countFriends(user2.id));

        userService.deleteFriend(user.id, user2.id);
        Assert.assertEquals("Conunt frineds error", 0, userService.countFriends(user.id));
        Assert.assertEquals("Conunt frineds error", 0, userService.countFriends(user2.id));
        userService.addFriend(user.id, test.id);
        Assert.assertEquals("Conunt frineds error", 1, userService.countFriends(user.id));
        Assert.assertEquals("Conunt frineds error", 1, userService.countFriends(test.id));
    }

    @Test
    public void testAll() {
        List<User> users = userService.getAllUsers();
        Assert.assertNotNull("Error", users);
        System.out.println(users);
    }



}
