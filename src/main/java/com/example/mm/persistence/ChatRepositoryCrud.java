package com.example.mm.persistence;

import com.example.mm.model.Chat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Win8.1 on 01.07.2017.
 */
public interface ChatRepositoryCrud extends CrudRepository<Chat, Long> {
}
