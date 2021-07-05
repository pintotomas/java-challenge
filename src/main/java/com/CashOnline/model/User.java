package com.CashOnline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String email;

    @NotBlank
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="user")
    private List<Loan> loans = Arrays.asList();

    public User(String mail, String firstName, String lastName) {
        this.email = mail;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
