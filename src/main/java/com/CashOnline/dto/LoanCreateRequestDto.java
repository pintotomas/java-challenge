package com.CashOnline.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class LoanCreateRequestDto {

    @Positive
    private Double total;

    @NotNull
    private Long userId;
}
