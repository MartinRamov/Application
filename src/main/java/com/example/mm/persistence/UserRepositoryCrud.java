package com.example.mm.persistence;

import com.example.mm.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Win8.1 on 28.06.2017.
 */
public interface UserRepositoryCrud extends CrudRepository<User, Long> {
    public User findUserByEmail(String email);
}
