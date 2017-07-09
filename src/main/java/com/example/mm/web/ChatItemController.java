package com.example.mm.web;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;
import com.example.mm.service.ChatItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by mila.gjurova on 7/5/2017.
 */
@RestController
@RequestMapping(value = "/chatItem", produces = "application/json")
public class ChatItemController {

    @Autowired
    ChatItemService chatItemService;

    //Tested
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ChatItem createChatItem(@RequestParam Long userId, @RequestParam Long chatId, @RequestParam String message) {
        return chatItemService.createChatItem(userId, chatId, message);
    }

    //doesn't work
    @RequestMapping(value = "/delete/{chatItemId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long chatItemId) {
        chatItemService.deleteChatItem(chatItemId);
    }


    //Tested
    @RequestMapping(value = "/get/{chatItemId}", method = RequestMethod.GET)
    public ChatItem getChatItem(@PathVariable Long chatItemId) {
        return chatItemService.getChatItem(chatItemId);
    }

    //Tested
    @RequestMapping(value = "/getUser/{chatItemId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long chatItemId) {
        return chatItemService.getUser(chatItemId);
    }

    //Tested
    @RequestMapping(value = "/getChat/{chatItemId}", method = RequestMethod.GET)
    public Chat getChat(@PathVariable Long chatItemId) {
        return chatItemService.getChat(chatItemId);
    }

    //Tested
    @RequestMapping(value = "/getAllItems/{chatId}", method = RequestMethod.GET)
    public Set<ChatItem> getChatItems(@PathVariable Long chatId) {
        return chatItemService.getChatItemsForChat(chatId);
    }


}
