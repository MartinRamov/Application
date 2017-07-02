//package com.example.mm.persistance;
//
//import com.example.mm.model.Activity;
//import com.example.mm.model.Meeting;
//import com.example.mm.model.User;
//import com.example.mm.persistence.UserRepository;
//import UserRepositoryCrud;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//
///**
// * Created by Win8.1 on 30.06.2017.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserPersistenceTests {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserRepositoryCrud userRepositoryCrud;
//    User u1=new User();
//    User u2=new User();
//
//    @Test
//    public void createUser(){
//
//        u1.firstname="Mila";
//        u1.lastname="Gjurova";
//        u1.email="mg@yah.com";
//        //da se istestira uste za meetings i activity
////        Set<Activity> activities = new TreeSet<>();
////        Activity a = new Activity();
////        a.title="aktivnost";
////        a.user=u1;
////        activities.add(a);
////        u1.activities=activities;
////        Set<Meeting> meetings = new TreeSet<>();
////        Meeting m = new Meeting();
////        m.title="Nov meeting";
////        meetings.add(m);
////        u1.meetings=meetings;
//
//        u2.firstname="Test";
//        u2.lastname="test";
//        u2.email="test@yah.com";
//        List<User>friends= new ArrayList<User>();
//        friends.add(u2);
//        u1.friends=friends;
//        userRepositoryCrud.save(u1);
//
//        User test= userRepositoryCrud.findOne(u1.id);
//
//        Assert.assertNotNull("Kreiran user",test);
//        Assert.assertEquals(test.id,u1.id);
//        Assert.assertEquals(test.firstname,u1.firstname);
//        //friend
//        Assert.assertEquals(true,userRepository.isFriend(u1.id,u2.id));
//        Assert.assertEquals(false,userRepository.isFriend(u1.id,Long.parseLong("35")));
//
//    }
//
//
//
//
//}
