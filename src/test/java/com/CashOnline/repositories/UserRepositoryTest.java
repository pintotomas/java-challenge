package com.CashOnline.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/schema.sql", "/data.sql"})
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldFindTwoUsers() {
        Assertions.assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void shouldFindUserByEmail() {
        Assertions.assertTrue(!userRepository.findByEmail("test@example.com").isEmpty());
    }

    @Test
    public void shouldNotFindUserByEmail() {
        Assertions.assertTrue(userRepository.findByEmail("test2@example.com").isEmpty());
    }
}
