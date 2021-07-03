package com.CashOnline.dto;

import com.CashOnline.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String mail;
    private String firstName;
    private String lastName;
    private Date createdAt;
    private Date updatedAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.mail = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = user.getCreated();
        this.updatedAt = user.getUpdated();
    }
}
