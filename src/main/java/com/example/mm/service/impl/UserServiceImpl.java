package com.example.mm.service.impl;

import com.example.mm.model.Activity;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public User createUser(String firstname, String lastname, String email, String password) {
        User user = new User();
        user.firstName = firstname;
        user.lastName = lastname;
        user.email = email;
        user.password = password;
        user = userRepositoryCrud.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, String firstname, String lastname, String email, String password) {
        User user = userRepositoryCrud.findOne(id);
        user.id = id;
        user.firstName = firstname;
        user.lastName = lastname;
        user.email = email;
        user.password = password;
        user = userRepositoryCrud.save(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepositoryCrud.findOne(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepositoryCrud.delete(userRepositoryCrud.findOne(id));
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
//        u2.friends.add(u1);
        userRepositoryCrud.save(u1);
//        userRepositoryCrud.save(u2);
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
        u2.friends.remove(u1);
        userRepositoryCrud.save(u1);
        userRepositoryCrud.save(u2);

    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepositoryCrud.findAll();
    }
}
