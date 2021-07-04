package com.CashOnline.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class FilterLoanDto {
    private Optional<Long> userId;
}
