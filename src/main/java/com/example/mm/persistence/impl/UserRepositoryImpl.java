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
    private UserRepositoryCrud userRepositoryCrud;

    @Override
    public boolean isFriend(Long id1, Long id2) {
        return false;
    }

    @Override
    public List<Activity> getActivities(Long userID) {
        return null;
    }

    @Override
    public List<Meeting> getMeetings(Long userID) {
        return null;
    }

}
