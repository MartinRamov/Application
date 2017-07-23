package com.example.mm.web;

import com.example.mm.exceptions.EmailAlreadyTakenException;
import com.example.mm.model.Activity;
import com.example.mm.model.Friend;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 02-Jul-17.
 */
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //Tested
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        System.out.println(ZonedDateTime.now());
        return userService.getAllUsers();
    }

    //Tested
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //Tested
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User createUser(@RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String email, @RequestParam String password) {
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(item -> {
            if(item.email.equals(email)) {
                throw new EmailAlreadyTakenException(email);
            }
        });
        return userService.createUser(firstName, lastName, email, !password.equals("")? password : null);
    }

    //Tested
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
//    public User updateUser(@RequestParam String firstName, @RequestParam String lastName,
//                           @RequestParam String email, @RequestParam String password,
//                           @PathVariable Long id) {
//        return userService.updateUser(id, firstName, lastName, email, password);
//    }

    @RequestMapping(value = "/updateFirstName/{id}", method = RequestMethod.PUT)
    public User updateFirstName(@RequestParam String firstName, @PathVariable Long id) {
        return userService.updateFirstName(id, firstName);
    }

    @RequestMapping(value = "/updateLastName/{id}", method = RequestMethod.PUT)
    public User updateLastName(@RequestParam String lastName, @PathVariable Long id) {
        return userService.updateLastName(id, lastName);
    }

    @RequestMapping(value = "/updateEmail/{id}", method = RequestMethod.PUT)
    public User updateEmail(@RequestParam String email, @PathVariable Long id) {
        return userService.updateEmail(id, email);
    }

    @RequestMapping(value = "/updatePassword/{id}", method = RequestMethod.PUT)
    public User updatePassword(@RequestParam String password, @PathVariable Long id) {
        return userService.updatePassword(id, password);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //Tested
    @RequestMapping(value = "/friends/{id1}/{id2}", method = RequestMethod.GET)
    public boolean checkIfFriends(@PathVariable Long id1, @PathVariable Long id2) {
        return userService.isFriend(id1, id2);
    }

    //Tested
    @RequestMapping(value = "/{id}/activities", method = RequestMethod.GET)
    public Set<Activity> getUserActivities(@PathVariable Long id) {
        return userService.getActivities(id);
    }

    //Tested
    @RequestMapping(value = "/{id}/meetings", method = RequestMethod.GET)
    public Set<Meeting> getUserMeetings(@PathVariable Long id) {
        return userService.getMeetings(id);
    }

    //Tested
    @RequestMapping(value = "/addFriend/{id1}/{id2}", method = RequestMethod.POST)
    public void addFriend(@PathVariable Long id1, @PathVariable Long id2) {
        userService.addFriend(id1, id2);
    }

    //Tested
    @RequestMapping(value = "/countFriends/{id}", method = RequestMethod.GET)
    public Integer getNumberOfFriends(@PathVariable Long id) {
        return userService.countFriends(id);
    }

    //Tested
    @RequestMapping(value = "/unfriend/{id1}/{id2}", method = RequestMethod.DELETE)
    public boolean unfriend(@PathVariable Long id1, @PathVariable Long id2) {
        logger.info("Into method");
        userService.deleteFriend(id1, id2);
        logger.info("Remove from friends {} -> {}", id1, id2);
        boolean result = !userService.isFriend(id1, id2);
        logger.info("Friend removed {}", result);
        return result;
    }

    //Tested
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestParam String email,@RequestParam String password){
        return userService.login(email,password);
    }

    //Tested
    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST)
    public User getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    //Tested
    @RequestMapping(value = "/getFriends/{id}", method = RequestMethod.GET)
    public List<Friend> getUserFriends(@PathVariable Long id) {
        return userService.getUserFriends(id);
    }

    //Tested
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public boolean userExists(@RequestParam String email) {
        return userService.getUserByEmail(email) != null;
    }

    //Tested
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<User> searchUsers(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }
}
