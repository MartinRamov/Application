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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ChatItem createChatItem(@RequestParam Long userId, @RequestParam Long chatId, @RequestParam String message) {
        return chatItemService.createChatItem(userId, chatId, message);
    }

    @RequestMapping(value = "/delete/{chatItemId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long chatItemId) {
        chatItemService.deleteChatItem(chatItemId);
    }



    @RequestMapping(value = "/get/{chatItemId}", method = RequestMethod.GET)
    public ChatItem getChatItem(@PathVariable Long chatItemId) {
        return chatItemService.getChatItem(chatItemId);
    }

    @RequestMapping(value = "/getUser/{chatItemId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long chatItemId) {
        return chatItemService.getUser(chatItemId);
    }

    @RequestMapping(value = "/getChat/{chatItemId}", method = RequestMethod.GET)
    public Chat getChat(@PathVariable Long chatItemId) {
        return chatItemService.getChat(chatItemId);
    }

    @RequestMapping(value = "/getAllItems/{chatId}")
    public Set<ChatItem> getChatItems(@PathVariable Long chatId) {
        return chatItemService.getChatItemsForChat(chatId);
    }


}
