package com.CashOnline.controller;

import com.CashOnline.dto.UserCreateRequestDto;
import com.CashOnline.dto.UserResponseDto;
import com.CashOnline.exceptions.UserAlreadyExistsException;
import com.CashOnline.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.CashOnline.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(UserController.URL_MAPPING_USERS)
public class UserController {
    public static final String URL_MAPPING_USERS = "/users";

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Collection<UserResponseDto>> getAll() {
        log.info("Requested to get all users");
        return ResponseEntity.ok(userService.getAll().stream().map(
                user -> new UserResponseDto(user)
        ).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable @Min(1) Long id) {
        try {
            log.info("Requested to get user with id {}", id);
            return ResponseEntity.ok(new UserResponseDto(userService.getById(id)));
        } catch (UserNotFoundException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
        try {
            log.info("Requested to create user with fields: email: " + userCreateRequestDto.getEmail()
            + ", first name: " + userCreateRequestDto.getFirstName() + " last name: " + userCreateRequestDto.getLastName());
            return ResponseEntity.ok(new UserResponseDto(userService.create(userCreateRequestDto)));

        } catch (UserAlreadyExistsException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
