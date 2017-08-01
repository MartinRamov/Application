package com.example.mm.service;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.User;

import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
public interface FriendRequestService {

    FriendRequest createFriendRequest(Long userSenderId, Long userReceiverId);

    User getSender(Long friendRequestId);

    User getReceiver(Long friendRequestId);

    Set<FriendRequest> getReceivedRequests(Long receiverId);

    void deleteFriendRequest(Long friendRequestId);

    FriendRequest acceptFriendRequest(Long friendRequestId);

    void declineFriendRequest(Long friendRequestId);

    Set<FriendRequest> getSentRequests(Long senderId);

}
