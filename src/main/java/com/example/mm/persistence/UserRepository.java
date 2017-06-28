package com.example.mm.persistence;

import com.example.mm.model.Activity;
import com.example.mm.model.Meeting;
import com.example.mm.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Win8.1 on 28.06.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    boolean isFriend(User user1, User user2);
    List<Activity> getActivities(User user);
    List<Meeting> getMeetings (User user);
//    Activity createActivity(Activity activity, User user);



}
