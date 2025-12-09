package org.example.splitwise.services;

import org.example.splitwise.model.User;
import org.example.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final UserRepository userRepository;

    public GroupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
