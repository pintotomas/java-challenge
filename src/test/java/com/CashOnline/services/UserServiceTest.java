package com.CashOnline.services;

import com.CashOnline.dto.UserCreateRequestDto;
import com.CashOnline.exceptions.UserAlreadyExistsException;
import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.User;
import com.CashOnline.repositories.UserRepository;
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
                new User("mail@example.com", "firstName", "lastName")));
        Assertions.assertDoesNotThrow(() -> userService.getById(1L));
    }

    @Test
    public void shouldThrowExceptionWhenCreateWithDuplicatedMail() {
        when(userRepository.findByEmail("mail@example.com")).thenReturn(Optional.of(
                new User("mail@example.com", "firstName", "LastName")));
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setEmail("mail@example.com");
        userCreateRequestDto.setFirstName("firstName");
        userCreateRequestDto.setLastName("lastName");
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.create(userCreateRequestDto));
    }

    @Test
    public void shouldNotThrowExceptionWhenCreateWithNotDuplicatedMail() {
        when(userRepository.findByEmail("mail@example.com")).thenReturn(Optional.empty());
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setEmail("mail@example.com");
        userCreateRequestDto.setFirstName("firstName");
        userCreateRequestDto.setLastName("lastName");
        Assertions.assertDoesNotThrow(() -> userService.create(userCreateRequestDto));
    }

    @Test
    public void shouldThrowExceptionWhenDeleteNonExistentUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteById(1L));
    }

    @Test
    public void shouldNotThrowExceptionWhenDeleteNonExistentUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of
                (new User("mail@example.com", "firstName", "lastName")));
        Assertions.assertDoesNotThrow(() -> userService.deleteById(1L));
    }
}
