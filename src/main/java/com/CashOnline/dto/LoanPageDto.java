package com.CashOnline.dto;

import com.CashOnline.model.Loan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class LoanPageDto {
    private List<LoanResponseDto> items;
    private PagingInformationDto paging;

    public LoanPageDto(Page<Loan> loans) {
        this.items = loans.getContent().stream().map(loan -> new LoanResponseDto(loan)).collect(Collectors.toList());
        this.paging = new PagingInformationDto(loans.getNumber() + 1, loans.getSize(), loans.getTotalPages());
    }
}
