package com.example.mm.service.impl;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;
import com.example.mm.persistence.ChatItemRepositoryCrud;
import com.example.mm.persistence.ChatRepositoryCrud;
import com.example.mm.persistence.UserRepositoryCrud;
import com.example.mm.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Win8.1 on 02.07.2017.
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepositoryCrud chatRepositoryCrud;

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Autowired
    private ChatItemRepositoryCrud chatItemRepositoryCrud;


    @Override
    public Chat getChat(Long chatId) {
        return chatRepositoryCrud.findOne(chatId);
    }

    @Override
    public Set<User> getParticipants(Long chatId) {
        Chat chat = chatRepositoryCrud.findOne(chatId);
        return chat.users;
    }

    @Override
    public Set<Chat> getChatsForUser(Long userId) {
        Set<Chat> chats = new HashSet<Chat>();
        Iterable<Chat> all = chatRepositoryCrud.findAll();
        Iterator<Chat> allChats = all.iterator();
        while (allChats.hasNext()) {
            Chat chat = allChats.next();
            for (User u : chat.users) {
                if (u.id == userId) {
                    chats.add(chat);
                }
            }

        }
        return chats;
    }

    @Override
    public void deleteChat(Long chatId) {
        Chat chat = chatRepositoryCrud.findOne(chatId);
        for(ChatItem chatItem : chat.chatItems) {
            chatItemRepositoryCrud.delete(chatItem.id);
            System.out.println("\nAAAAAA\n");
        }
        chat.chatItems.clear();
        chat = chatRepositoryCrud.save(chat);
        chatRepositoryCrud.delete(chatId);
    }

    @Override
    public void addUser(Long chatId, Long userId) {
        Chat c = chatRepositoryCrud.findOne(chatId);
        c.users.add(userRepositoryCrud.findOne(userId));
        User u = userRepositoryCrud.findOne(userId);
        u.chats.add(c);
        userRepositoryCrud.save(u);
        chatRepositoryCrud.save(c);
    }

    @Override
    public Set<ChatItem> getChatItems(Long chatId) {
        return chatRepositoryCrud.findOne(chatId).chatItems;
    }

    @Override
    public Chat createChat() {
        Chat c = new Chat();
        c = chatRepositoryCrud.save(c);
        return c;
    }

    @Override
    public void addChatItem(Long chatId, Long chatItemId) {
        ChatItem cI = chatItemRepositoryCrud.findOne(chatItemId);
        Chat c = chatRepositoryCrud.findOne(chatId);
        c.chatItems.add(cI);
        chatRepositoryCrud.save(c);

    }

    @Override
    public void cleanChat(Long chat_id) {
        Chat chat=chatRepositoryCrud.findOne(chat_id);
        for(ChatItem chatItem: chat.chatItems)
        {
            chat.chatItems.remove(chatItem);
            chatItemRepositoryCrud.delete(chatItem);
            chatRepositoryCrud.save(chat);
        }

        for(User u:chat.users){
            u.chats.remove(chat);
            chat.users.remove(u);
            chatRepositoryCrud.save(chat);
            userRepositoryCrud.save(u);
        }

        chatRepositoryCrud.delete(chat);

    }
}
