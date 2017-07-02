package com.example.mm.service.impl;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.User;
import com.example.mm.persistence.FriendRequestRepositoryCrud;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    FriendRequestRepositoryCrud friendRequestRepositoryCrud;
    @Autowired
    UserRepositoryCrud userRepositoryCrud;


    @Override
    public FriendRequest createFriendRequest(Long userSenderId, Long userRecieverId) {
        User sender = userRepositoryCrud.findOne(userSenderId);
        User reciever = userRepositoryCrud.findOne(userRecieverId);
        FriendRequest fr = new FriendRequest();
        fr.sender = sender;
        fr.receiver = reciever;
        friendRequestRepositoryCrud.save(fr);
        return fr;
    }

    @Override
    public User getSender(Long friendRequestId) {
        FriendRequest fr = friendRequestRepositoryCrud.findOne(friendRequestId);
        User sender = userRepositoryCrud.findOne(fr.sender.id);
        return sender;
    }

    @Override
    public User getReciever(Long friendRequestId) {
        FriendRequest fr = friendRequestRepositoryCrud.findOne(friendRequestId);
        User receiver = userRepositoryCrud.findOne(fr.receiver.id);
        return receiver;
    }

    @Override
    public Set<FriendRequest> getRecievedRequests(Long recieverId) {
        Set<FriendRequest> set = new HashSet<FriendRequest>();
        Iterable<FriendRequest> all = friendRequestRepositoryCrud.findAll();
        Iterator friendRequests = all.iterator();
        while (friendRequests.hasNext()) {
            FriendRequest fr = (FriendRequest) friendRequests.next();
            if (fr.receiver.id == recieverId) {
                set.add(fr);
            }

        }
        return set;
    }

    @Override
    public void deleteFriendRequest(Long friendRequestId) {
        friendRequestRepositoryCrud.delete(friendRequestId);

    }

    @Override
    public FriendRequest acceptFriendRequest(Long friendRequestId) {
        FriendRequest friendRequest = friendRequestRepositoryCrud.findOne(friendRequestId);
        User sender = userRepositoryCrud.findOne(friendRequest.sender.id);
        User receiver = userRepositoryCrud.findOne(friendRequest.receiver.id);
        sender.friends.add(receiver);

        userRepositoryCrud.save(sender);

        friendRequestRepositoryCrud.delete(friendRequestId);
        return friendRequest;
    }

    @Override
    public void declineFriendRequest(Long friendRequestId) {
        friendRequestRepositoryCrud.delete(friendRequestId);
    }
}
