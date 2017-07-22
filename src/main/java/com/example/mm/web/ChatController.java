package com.example.mm.web;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;
import com.example.mm.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Chat controller
 */
@RestController
@RequestMapping(value = "/chat", produces = "application/json")
public class ChatController {

    @Autowired
    private ChatService chatService;

    //Tested
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Chat getChat(@PathVariable Long id) {
        return chatService.getChat(id);
    }

    //Tested
    @RequestMapping(value = "/participants/{chatId}", method = RequestMethod.GET)
    public Set<User> getParticipants(@PathVariable Long chatId){
        return chatService.getParticipants(chatId);
    }

    //tested
    @RequestMapping(value = "/getForUser/{userId}", method = RequestMethod.GET)
    public Set<Chat> getChatsForUser(@PathVariable Long userId){
        return chatService.getChatsForUser(userId);
    }

    //Doesn't work
    @RequestMapping(value = "/delete/{chatId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long chatId){


        chatService.cleanChat(chatId);
    }

    //Tested:OK
    @RequestMapping(value = "/addUser/{chatId}", method = RequestMethod.POST)
    public void addUser(@PathVariable Long chatId, @RequestParam Long userId){
        chatService.addUser(chatId,userId);
    }

    //Tested
    @RequestMapping(value = "/getChatItems/{chatId}", method = RequestMethod.GET)
    public Set<ChatItem> getChatItems(@PathVariable Long chatId){
        return chatService.getChatItems(chatId);
    }

    //Tested
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Chat createChat(){
        return chatService.createChat();
    }

    //We don't need this method because when we create chatItem we create it with chat id :)
    @RequestMapping(value = "/addChatItem/{chatId}", method = RequestMethod.PUT)
    public void addChatItem(@PathVariable Long chatId, @RequestParam Long chatItemId){
        chatService.addChatItem(chatId,chatItemId);
    }

}
