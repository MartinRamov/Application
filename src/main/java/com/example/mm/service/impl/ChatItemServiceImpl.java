package com.example.mm.service.impl;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;
import com.example.mm.persistence.crud.ChatItemRepositoryCrud;
import com.example.mm.persistence.crud.ChatRepositoryCrud;
import com.example.mm.persistence.crud.UserRepositoryCrud;
import com.example.mm.service.ChatItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Win8.1 on 02.07.2017.
 */
@Service
public class ChatItemServiceImpl implements ChatItemService {
    @Autowired
    ChatItemRepositoryCrud chatItemRepositoryCrud;
    @Autowired
    UserRepositoryCrud userRepositoryCrud;
    @Autowired
    ChatRepositoryCrud chatRepositoryCrud;


    @Override
    public ChatItem createChatItem(Long userId, Long chatId, String message) {
        ChatItem chatItem = new ChatItem();
        chatItem.user = userRepositoryCrud.findOne(userId);
        chatItem.chat = chatRepositoryCrud.findOne(chatId);
        chatItem.message = message;
        chatItemRepositoryCrud.save(chatItem);
        return chatItem;
    }

    @Override
    public void deleteChatItem(Long chatItemId) {
        chatItemRepositoryCrud.delete(chatItemId);
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
}
