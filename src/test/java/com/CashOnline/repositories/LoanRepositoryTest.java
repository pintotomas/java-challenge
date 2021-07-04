package com.CashOnline.repositories;

import com.CashOnline.dto.FilterLoanDto;
import com.CashOnline.model.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static com.CashOnline.repositories.specifications.LoanSpecifications.byFilter;

@Sql({"/schema.sql", "/data.sql"})
@SpringBootTest
public class LoanRepositoryTest {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldFindFiveLoans() {
        Assertions.assertEquals(5, loanRepository.findAll().size());
    }

    @Test
    public void shouldFindThreeLoansForFirstUser() {
        Assertions.assertEquals(3, loanRepository.count(byFilter(new FilterLoanDto(java.util.Optional.of(1L)))));
    }

    @Test
    public void shouldNotFindLoansAfterUserDelete() {
        userRepository.deleteById(1L);
        Assertions.assertEquals(0, loanRepository.count(byFilter(new FilterLoanDto(java.util.Optional.of(1L)))));
    }

    @Test
    public void shouldFindLoansFilteredAndPaginated() {
        Page<Loan> loans = loanRepository.findAll(byFilter(new FilterLoanDto(java.util.Optional.of(1L))), PageRequest.of(1, 3));
        Assertions.assertEquals(3, loans.getSize());
    }
}
