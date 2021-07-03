package com.CashOnline.service;

import com.CashOnline.dto.UserCreateRequestDto;
import com.CashOnline.dto.UserResponseDto;
import com.CashOnline.exceptions.UserAlreadyExistsException;
import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.User;
import com.CashOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public User create(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(userCreateRequestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userCreateRequestDto.getEmail() + "Already exists");
        }
        return userRepository.save(new User(userCreateRequestDto.getEmail(),
                userCreateRequestDto.getFirstName(),
                userCreateRequestDto.getLastName()));

    }
}
