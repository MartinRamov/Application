package com.example.mm.service;

import com.example.mm.model.Activity;
import com.example.mm.model.Friend;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Win8.1 on 30.06.2017.
 */
public interface UserService {

    User createUser(String firstname, String lastname, String email, String password);

    User makeUserPremium(Long userId);

    User updateUser(Long id, String firstname, String lastname, String email, String password);

    User updateFirstName(Long id, String firstName);

    User updateLastName(Long id, String lastName);

    User updateEmail(Long id, String email);

    User changePassword(Long id, String oldPassword, String newPassword);

    User updatePassword(Long id, String password);

    User getUserById(Long id);

    void deleteUser(Long id);

    boolean isFriend(Long id1, Long id2);

    Set<Activity> getActivities(Long userID);

    Set<Meeting> getMeetings(Long userID);

    void addFriend(Long id1, Long id2);

    int countFriends(Long id1);

    void deleteFriend(Long id1, Long id2);

    List<User> getAllUsers();

    boolean login(String email, String password);

    User getUserByEmail(String email);

    List<Friend> getUserFriends(Long id);

    List<User> searchUsers(String query);
}
