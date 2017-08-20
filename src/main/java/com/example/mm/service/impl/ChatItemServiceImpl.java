package com.example.mm.service.impl;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;
import com.example.mm.persistence.ChatItemRepositoryCrud;
import com.example.mm.persistence.ChatRepositoryCrud;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.ChatItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Win8.1 on 02.07.2017.
 */
@Service
public class ChatItemServiceImpl implements ChatItemService {

    @Autowired
    private ChatItemRepositoryCrud chatItemRepositoryCrud;

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Autowired
    private ChatRepositoryCrud chatRepositoryCrud;


    @Override
    public ChatItem createChatItem(Long userId, Long chatId, String message) {
        ChatItem chatItem = new ChatItem();
        chatItem.user = userRepositoryCrud.findOne(userId);
        chatItem.chat = chatRepositoryCrud.findOne(chatId);
        chatItem.message = message;
        chatItem.timeSent = LocalDateTime.now();
        return chatItemRepositoryCrud.save(chatItem);
    }

    @Override
    public void deleteChatItem(Long chatItemId) {
        ChatItem chatItem = chatItemRepositoryCrud.findOne(chatItemId);
        Chat chat = chatRepositoryCrud.findOne(chatItem.chat.id);
        chat.chatItems.remove(chatItem);
        chatRepositoryCrud.save(chat);
        chatItemRepositoryCrud.delete(chatItem);
    }

    @Override
    public ChatItem getChatItem(Long chatItemId) {
        return chatItemRepositoryCrud.findOne(chatItemId);
    }

    @Override
    public User getUser(Long chatItemId) {
        return chatItemRepositoryCrud.findOne(chatItemId).user;
    }

    @Override
    public Chat getChat(Long chatItemId) {
        return chatItemRepositoryCrud.findOne(chatItemId).chat;
    }

    @Override
    public Set<ChatItem> getChatItemsForChat(Long chatId) {
        return chatRepositoryCrud.findOne(chatId).chatItems;
    }

    @Override
    public Set<ChatItem> getChatItemsAfterId(Long chatId, Long chatItemId) {
        Chat chat = chatRepositoryCrud.findOne(chatId);
        Set<ChatItem> chatItemSet = chat.chatItems;
        chatItemSet = chatItemSet.stream().filter(chatItem -> chatItem.id > chatItemId).collect(Collectors.toSet());
        return chatItemSet;
    }
}
