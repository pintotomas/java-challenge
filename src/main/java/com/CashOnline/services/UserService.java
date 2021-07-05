package com.CashOnline.services;

import com.CashOnline.dto.UserCreateRequestDto;
import com.CashOnline.exceptions.UserAlreadyExistsException;
import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.User;
import com.CashOnline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    public User create(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(userCreateRequestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userCreateRequestDto.getEmail() + " already exists");
        }
        return userRepository.save(new User(userCreateRequestDto.getEmail(),
                userCreateRequestDto.getFirstName(),
                userCreateRequestDto.getLastName()));

    }

    public void deleteById(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isEmpty())  {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
