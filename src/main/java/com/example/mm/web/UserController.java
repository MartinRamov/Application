package com.example.mm.web;

import com.example.mm.model.User;
import com.example.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Martin on 02-Jul-17.
 */
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User createUser(@RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String email, @RequestParam String password) {
        return userService.createUser(firstName, lastName, email, password);
    }

}
