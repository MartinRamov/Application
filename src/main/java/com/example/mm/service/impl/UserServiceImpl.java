package com.example.mm.service.impl;

import com.example.mm.model.*;
import com.example.mm.persistence.FriendRequestRepositoryCrud;
import com.example.mm.persistence.SearchRepository;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private SearchRepository searchRepository;

    @Override
    public User createUser(String firstname, String lastname, String email, String password) {
        User user = new User();
        user.firstName = firstname;
        user.lastName = lastname;
        user.email = email;
        if (password != null)
            user.password = passwordEncoder.encode(password);
        user.isPremiumUser = false;
        return userRepositoryCrud.save(user);
    }

    @Override
    public User makeUserPremium(Long userId) {
        User user = userRepositoryCrud.findOne(userId);
        user.isPremiumUser = true;
        return userRepositoryCrud.save(user);
    }

    @Override
    public User updateUser(Long id, String firstname, String lastname, String email, String password) {
        User user = userRepositoryCrud.findOne(id);
        if (firstname != null) {
            user.firstName = firstname;
        }
        if (lastname != null) {
            user.lastName = lastname;
        }
        if (email != null) {
            user.email = email;
        }
        if (password != null) {
            user.password = passwordEncoder.encode(password);
        }
        return userRepositoryCrud.save(user);
    }

    @Override
    public User updateFirstName(Long id, String firstName) {
        User user = userRepositoryCrud.findOne(id);
        user.firstName = firstName;
        return userRepositoryCrud.save(user);
    }

    @Override
    public User updateLastName(Long id, String lastName) {
        User user = userRepositoryCrud.findOne(id);
        user.lastName = lastName;
        return userRepositoryCrud.save(user);
    }

    @Override
    public User updateEmail(Long id, String email) {
        User user = userRepositoryCrud.findOne(id);
        user.email = email;
        return userRepositoryCrud.save(user);
    }

    @Override
    public User updatePassword(Long id, String password) {
        User user = userRepositoryCrud.findOne(id);
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
        for (FriendRequest f : user.sentRequests) {
            friendRequestRepositoryCrud.delete(f.id);
        }
        for (FriendRequest f : user.receivedRequests) {
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
        User user = userRepositoryCrud.findUserByEmail(email);
        return user != null && passwordEncoder.matches(password, user.password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepositoryCrud.findUserByEmail(email);
    }

    @Override
    public List<Friend> getUserFriends(Long id) {
        User user = userRepositoryCrud.findOne(id);
        List<Friend> results = new ArrayList<>();
        user.friends.forEach(item -> {
            Friend friend = new Friend(item.id, item.firstName, item.lastName, item.email);
            results.add(friend);
        });
        return results;
    }

    @Override
    public List<User> searchUsers(String query) {
        return searchRepository.searchPhrase(User.class, query, "firstName", "lastName");
    }
}
