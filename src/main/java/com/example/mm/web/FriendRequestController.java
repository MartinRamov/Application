package com.example.mm.web;

import com.example.mm.model.FriendRequest;
import com.example.mm.model.User;
import com.example.mm.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by mila.gjurova on 7/6/2017.
 */
@RestController
@RequestMapping(value = "/friendRequest", produces = "application/json")
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public FriendRequest createFriendRequest(@RequestParam Long userSenderId, @RequestParam Long userReceiverId) {
        return friendRequestService.createFriendRequest(userSenderId, userReceiverId);
    }

    @RequestMapping(value = "/getSender/{requestId}", method = RequestMethod.GET)
    public User getSender(@PathVariable Long requestId) {
        return friendRequestService.getSender(requestId);
    }

    @RequestMapping(value = "/getReceiver/{requestId}", method = RequestMethod.GET)
    public User getReceiver(@PathVariable Long requestId) {
        return friendRequestService.getReceiver(requestId);
    }

    @RequestMapping(value = "/getReceivedRequests/{receiverId}", method = RequestMethod.GET)
    private Set<FriendRequest> getReceivedFriendRequests(@PathVariable Long receiverId) {
        return friendRequestService.getReceivedRequests(receiverId);
    }

    @RequestMapping(value = "/delete/{requestId}", method = RequestMethod.DELETE)
    private void delete(@PathVariable Long requestId) {
        friendRequestService.deleteFriendRequest(requestId);
    }

    @RequestMapping(value = "/accept/{requestId}", method = RequestMethod.POST)
    private FriendRequest accept(@PathVariable Long requestId) {
        return friendRequestService.acceptFriendRequest(requestId);
    }

    @RequestMapping(value = "/decline/{requestId}", method = RequestMethod.DELETE)
    private void decline(@PathVariable Long requestId) {
        friendRequestService.declineFriendRequest(requestId);
    }

}
