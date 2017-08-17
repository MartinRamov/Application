package com.example.mm.service;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;

import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
public interface ChatService {

    Chat getChat(Long chatId);

    Set<User> getParticipants(Long chatId);

    Set<Chat> getChatsForUser(Long userId);

    void deleteChat(Long chatId);

    void addUser(Long chatId, Long userId);

    Set<ChatItem> getChatItems(Long chatId);

    Chat createChat();

    void addChatItem(Long chatId, Long chatItemId);

    void cleanChat(Long chat_id);

    void removeUser(Long chatId, Long userId);

}
