package com.CashOnline.services;

import com.CashOnline.dto.LoanPageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@Sql({"/schema.sql", "/data.sql"})
@SpringBootTest
public class LoanServiceTest {

    @Autowired
    LoanService loanService;

    @Test
    public void shouldFilterByUser() {
        LoanPageDto loanDtoPage = loanService.getLoansFilteredAndPaginated(0, 10, Optional.of(1L));
        Assertions.assertEquals(loanDtoPage.getItems().size(), 3);
    }
}
