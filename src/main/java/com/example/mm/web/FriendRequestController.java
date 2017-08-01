package com.example.mm.web;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.User;
import com.example.mm.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Friend Request Controller
 */
@RestController
@RequestMapping(value = "/friendRequest", produces = "application/json")
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    //Tested
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public FriendRequest createFriendRequest(@RequestParam Long userSenderId, @RequestParam Long userReceiverId) {
        return friendRequestService.createFriendRequest(userSenderId, userReceiverId);
    }

    //Tested
    @RequestMapping(value = "/getSender/{requestId}", method = RequestMethod.GET)
    public User getSender(@PathVariable Long requestId) {
        return friendRequestService.getSender(requestId);
    }


    //Tested
    @RequestMapping(value = "/getReceiver/{requestId}", method = RequestMethod.GET)
    public User getReceiver(@PathVariable Long requestId) {
        return friendRequestService.getReceiver(requestId);
    }

    //Tested
    @RequestMapping(value = "/getReceivedRequests/{receiverId}", method = RequestMethod.GET)
    private Set<FriendRequest> getReceivedFriendRequests(@PathVariable Long receiverId) {
        return friendRequestService.getReceivedRequests(receiverId);
    }

    //Tested
    @RequestMapping(value = "/delete/{requestId}", method = RequestMethod.DELETE)
    private void delete(@PathVariable Long requestId) {
        friendRequestService.deleteFriendRequest(requestId);
    }

    //Tested
    @RequestMapping(value = "/accept/{requestId}", method = RequestMethod.POST)
    private FriendRequest accept(@PathVariable Long requestId) {
        return friendRequestService.acceptFriendRequest(requestId);
    }

    //Tested
    @RequestMapping(value = "/decline/{requestId}", method = RequestMethod.DELETE)
    private void decline(@PathVariable Long requestId) {
        friendRequestService.declineFriendRequest(requestId);
    }

    //The delete and decline doesn't work if they are connected with notification.. The same problem as user


    @RequestMapping(value = "/getSent/{senderId}", method = RequestMethod.GET)
    private Set<FriendRequest> getSentRequests(@PathVariable Long senderId) {
        return friendRequestService.getSentRequests(senderId);
    }

    @RequestMapping(value = "/getUserReceivers/{senderId}", method = RequestMethod.GET)
    private List<User> getUserReceiversForSender(@PathVariable Long senderId) {
        Set<FriendRequest> requests = friendRequestService.getSentRequests(senderId);
        List<User> results = new ArrayList<>();
        for(FriendRequest fr : requests) {
            results.add(fr.receiver);
        }
        return results;
    }
}
