package org.example.splitwise.controllers;

import org.example.splitwise.model.User;
import org.example.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody User getUser(@PathVariable(name="id") Long id){
          return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}

