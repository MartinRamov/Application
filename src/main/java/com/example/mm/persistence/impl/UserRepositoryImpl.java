package com.example.mm.persistence.impl;

import com.example.mm.model.Activity;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import com.example.mm.persistence.UserRepository;
import com.example.mm.persistence.crud.UserRepositoryCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Win8.1 on 28.06.2017.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserRepositoryCrud userRepositoryCrud;


    @Override
    public boolean isFriend(User user1, User user2) {
        return false;
    }

    @Override
    public List<Activity> getActivities(User user) {
        return null;
    }

    @Override
    public List<Meeting> getMeetings(User user) {
        return null;
    }


}
