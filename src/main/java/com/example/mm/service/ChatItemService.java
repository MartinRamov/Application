package com.example.mm.service;

import com.example.mm.model.Chat;
import com.example.mm.model.ChatItem;
import com.example.mm.model.User;

import java.util.Set;

/**
 * Created by Win8.1 on 01.07.2017.
 */
public interface ChatItemService {

    ChatItem createChatItem(Long userId, Long chatId, String message);

    void deleteChatItem(Long chatItemId);

    ChatItem getChatItem(Long chatItemId);

    User getUser(Long chatItemId);

    Chat getChat(Long chatItemId);

    Set<ChatItem> getChatItemsForChat(Long chatId);



}
