package com.example.mm.persistence.crud;

import com.example.mm.model.ChatItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Win8.1 on 01.07.2017.
 */
public interface ChatItemRepositoryCrud extends CrudRepository<ChatItem,Long> {
}
