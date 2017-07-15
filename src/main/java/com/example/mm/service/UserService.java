package com.example.mm.service;

import com.example.mm.model.Activity;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Win8.1 on 30.06.2017.
 */
public interface UserService {
    User createUser(String firstname, String lastname, String email, String username, String password);

    User updateUser(Long id, String firstname, String lastname, String email, String username, String password);

    User getUserById(Long id);

    void deleteUser(Long id);

    boolean isFriend(Long id1, Long id2);

    Set<Activity> getActivities(Long userID);

    Set<Meeting> getMeetings(Long userID);

    void addFriend(Long id1, Long id2);

    int countFriends(Long id1);

    void deleteFriend(Long id1, Long id2);

    List<User> getAllUsers();
}
