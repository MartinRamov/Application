package com.example.mm.persistence;

import com.example.mm.model.Activity;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Win8.1 on 28.06.2017.
 */
public interface UserRepository {

    boolean isFriend(Long id1, Long id2);
    List<Activity> getActivities(Long userID);
    List<Meeting> getMeetings (Long userID);
//    Activity createActivity(Activity activity, User user);



}
