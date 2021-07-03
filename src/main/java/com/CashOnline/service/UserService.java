package com.CashOnline.service;

import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.User;
import com.CashOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id" + id + "not found"));
    }

    public Collection<User> getAll() {
        return userRepository.findAll();
    }
}
