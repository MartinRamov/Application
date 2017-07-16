package com.example.mm.service.impl;

import com.example.mm.model.Activity;
import com.example.mm.model.FriendRequest;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.persistence.FriendRequestRepositoryCrud;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Win8.1 on 30.06.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Autowired
    private FriendRequestRepositoryCrud friendRequestRepositoryCrud;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(String firstname, String lastname, String email , String password) {
        User user = new User();
        user.firstName = firstname;
        user.lastName = lastname;
        user.email = email;
        user.password = passwordEncoder.encode(password);
        user = userRepositoryCrud.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, String firstname, String lastname, String email, String password) {
        User user = userRepositoryCrud.findOne(id);
        if(id!=null)
        user.id = id;
        if(firstname!=null)
        user.firstName = firstname;
        if(lastname!=null)
        user.lastName = lastname;
        if(email!=null)
        user.email = email;
        if(password!=null)
        user.password = passwordEncoder.encode(password);
        return userRepositoryCrud.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepositoryCrud.findOne(id);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepositoryCrud.findOne(id);
        user.chats.clear();
        user.friends.clear();
        user.friendOf.clear();
        user.meetings.clear();
        user.activities.clear();
        user.chatItems.clear();
        user.notifications.clear();
        for(FriendRequest f : user.sentRequests) {
            friendRequestRepositoryCrud.delete(f.id);
        }
        for(FriendRequest f : user.receivedRequests) {
            friendRequestRepositoryCrud.delete(f.id);
        }
        user.sentRequests.clear();
        user.receivedRequests.clear();
        user = userRepositoryCrud.save(user);
        userRepositoryCrud.delete(user);
    }

    @Override
    public boolean isFriend(Long id1, Long id2) {
        boolean friend = false;
        User u1 = userRepositoryCrud.findOne(id1);
        User u2 = userRepositoryCrud.findOne(id2);
        if (u1 != null && u2 != null) {
            for (User u : u1.friends) {
                if (u2.id.equals(u.id)) {
                    friend = true;
                    break;
                }
            }
        }
        return friend;
    }

    @Override
    public Set<Activity> getActivities(Long userID) {
        User user = userRepositoryCrud.findOne(userID);

        return user.activities;
    }

    @Override
    public Set<Meeting> getMeetings(Long userID) {
        User user = userRepositoryCrud.findOne(userID);

        return user.meetings;
    }

    @Override
    public void addFriend(Long id1, Long id2) {
        User u1 = userRepositoryCrud.findOne(id1);
        User u2 = userRepositoryCrud.findOne(id2);
        u1.friends.add(u2);
        u1.friendOf.add(u2);
        userRepositoryCrud.save(u1);
    }

    @Override
    public int countFriends(Long id1) {
        User user = userRepositoryCrud.findOne(id1);
        return user.friends.size();
    }

    @Override
    public void deleteFriend(Long id1, Long id2) {
        User u1 = userRepositoryCrud.findOne(id1);
        User u2 = userRepositoryCrud.findOne(id2);
        u1.friends.remove(u2);
        u1.friendOf.remove(u2);
        userRepositoryCrud.save(u1);
//        userRepositoryCrud.save(u2);

    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepositoryCrud.findAll();
    }

    @Override
    public boolean login(String email, String password) {
       User user=userRepositoryCrud.findUserByEmail(email);
        System.out.println(user.password);

        String pass= passwordEncoder.encode(password);
        System.out.print(pass);
        if(pass.equals(user.password))
            return true;
        return false;
    }
}
