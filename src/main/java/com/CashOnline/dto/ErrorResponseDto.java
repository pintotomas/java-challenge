package com.CashOnline.dto;

import com.CashOnline.model.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {
    private ErrorCode errorCode;
    private String description;
}
