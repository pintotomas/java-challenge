package com.CashOnline.dto;

import com.CashOnline.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponseDto extends AuditModelDto {

    private Long id;
    private String mail;
    private String firstName;
    private String lastName;
    private List<LoanResponseDto> loans;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.mail = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = user.getCreated();
        this.updatedAt = user.getUpdated();
        this.loans = user.getLoans().stream().map(loan -> new LoanResponseDto(loan)).collect(Collectors.toList());
    }
}
