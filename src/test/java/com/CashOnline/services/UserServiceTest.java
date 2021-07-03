package com.CashOnline.services;

import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.User;
import com.CashOnline.repository.UserRepository;
import com.CashOnline.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void ShouldThrowExceptionWhenGetByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getById(1L));
    }

    @Test
    public void ShouldNotThrowExceptionWhenGetByIdFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(
                new User("mail@example.com", "firstName", "LastName")));
        Assertions.assertDoesNotThrow(() -> userService.getById(1L));
    }
}
