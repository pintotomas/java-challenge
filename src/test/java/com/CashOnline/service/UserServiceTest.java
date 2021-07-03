package com.CashOnline.service;

import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.User;
import com.CashOnline.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldThrowExceptionWhenGetByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getById(1L));
    }

    @Test
    public void shouldNotThrowExceptionWhenGetByIdFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(
                new User("mail@example.com", "firstName", "LastName")));
        Assertions.assertDoesNotThrow(() -> userService.getById(1L));
    }
}
