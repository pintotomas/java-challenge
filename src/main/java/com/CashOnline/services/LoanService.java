package com.CashOnline.services;

import com.CashOnline.dto.FilterLoanDto;
import com.CashOnline.dto.LoanCreateRequestDto;
import com.CashOnline.dto.LoanPageDto;
import com.CashOnline.exceptions.LoanCannotBeNegativeException;
import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.Loan;
import com.CashOnline.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.CashOnline.repositories.specifications.LoanSpecifications.byFilter;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserService userService;

    public LoanPageDto getLoansFilteredAndPaginated(
            Integer page,
            Integer size,
            Optional<Long> userId) {
        Pageable pageRequest = PageRequest.of(page, size);
        FilterLoanDto filter = new FilterLoanDto(userId);
        Page<Loan> loans = loanRepository.findAll(byFilter(filter), pageRequest);
        return new LoanPageDto(loans);
    }

    public Loan create(LoanCreateRequestDto loanCreateRequestDto) throws UserNotFoundException, LoanCannotBeNegativeException {
        Loan loan = new Loan(loanCreateRequestDto.getTotal(), userService.getById(loanCreateRequestDto.getUserId()));
        if (loanCreateRequestDto.getTotal() < 0) {
            throw new LoanCannotBeNegativeException("Loan cannot be negative: " + loanCreateRequestDto.getTotal());
        }
        return loanRepository.save(loan);
    }
}
