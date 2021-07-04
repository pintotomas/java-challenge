package com.CashOnline.services;

import com.CashOnline.dto.FilterLoanDto;
import com.CashOnline.dto.LoanPageDto;
import com.CashOnline.dto.LoanResponseDto;
import com.CashOnline.dto.PagingInformationDto;
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

    public LoanPageDto getLoansFilteredAndPaginated(
            Integer page,
            Integer size,
            Optional<Long> userId) {
        Pageable pageRequest = PageRequest.of(page, size);
        FilterLoanDto filter = new FilterLoanDto(userId);
        Page<Loan> loans = loanRepository.findAll(byFilter(filter), pageRequest);
        return new LoanPageDto(loans);
    }
}
