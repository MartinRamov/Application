package com.example.mm.service.impl;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.User;
import com.example.mm.persistence.FriendRequestRepositoryCrud;
import com.example.mm.persistence.NotificationRepositoryCrud;
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
    private FriendRequestRepositoryCrud friendRequestRepositoryCrud;

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Autowired
    private NotificationRepositoryCrud notificationRepositoryCrud;


    @Override
    public FriendRequest createFriendRequest(Long userSenderId, Long userRecieverId) {
        User sender = userRepositoryCrud.findOne(userSenderId);
        User receiver = userRepositoryCrud.findOne(userRecieverId);
        FriendRequest fr = new FriendRequest();
        fr.sender = sender;
        fr.receiver = receiver;
        fr = friendRequestRepositoryCrud.save(fr);
        sender.sentRequests.add(fr);
        receiver.receivedRequests.add(fr);
        userRepositoryCrud.save(sender);
        userRepositoryCrud.save(receiver);
        return fr;
    }

    @Override
    public User getSender(Long friendRequestId) {
        FriendRequest fr = friendRequestRepositoryCrud.findOne(friendRequestId);
        User sender = userRepositoryCrud.findOne(fr.sender.id);
        return sender;
    }

    @Override
    public User getReceiver(Long friendRequestId) {
        FriendRequest fr = friendRequestRepositoryCrud.findOne(friendRequestId);
        User receiver = userRepositoryCrud.findOne(fr.receiver.id);
        return receiver;
    }

    @Override
    public Set<FriendRequest> getReceivedRequests(Long recieverId) {
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
        FriendRequest friendRequest = friendRequestRepositoryCrud.findOne(friendRequestId);
        friendRequest.sender = null;
        friendRequest.receiver = null;
        friendRequest = friendRequestRepositoryCrud.save(friendRequest);
        friendRequestRepositoryCrud.delete(friendRequest.id);
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
