package com.CashOnline.controller;

import com.CashOnline.dto.UserDto;
import com.CashOnline.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CashOnline.service.UserService;

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
    public ResponseEntity<Collection<UserDto>> getAll() {
        log.info("Requested to get all users");
        return ResponseEntity.ok(userService.getAll().stream().map(
                user -> new UserDto(user)
        ).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable @Min(1) Long id) {
        try {
            log.info("Requested to get user with id {}", id);
            return ResponseEntity.ok(new UserDto(userService.getById(id)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
