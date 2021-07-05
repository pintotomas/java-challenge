package com.CashOnline.controllers;

import com.CashOnline.dto.ErrorResponseDto;
import com.CashOnline.dto.LoanCreateRequestDto;
import com.CashOnline.dto.LoanPageDto;
import com.CashOnline.dto.LoanResponseDto;
import com.CashOnline.exceptions.LoanCannotBeNegativeException;
import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.model.ErrorCode;
import com.CashOnline.services.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(LoanController.URL_MAPPING_USERS)
public class LoanController {
    public static final String URL_MAPPING_USERS = "/loans";

    @Autowired
    LoanService loanService;

    @GetMapping
    public ResponseEntity<LoanPageDto> getLoansFilteredAndPaginated(
            @RequestParam("page") @Min(1) Integer page,
            @RequestParam("size") @Min(1) Integer size,
            @RequestParam(value = "user_id", required = false) Optional<Long> userId
            ) {
        log.info("Requested to filter loans with params: page {} size {} user_id {}", page, size, userId.orElse(null));
        return ResponseEntity.ok(loanService.getLoansFilteredAndPaginated(page - 1, size, userId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid LoanCreateRequestDto loanCreateRequestDto) {
        try {
            log.info("Requested to create loan with fields: userId: " + loanCreateRequestDto.getUserId()
                    + ", total: " + loanCreateRequestDto.getTotal());
            return ResponseEntity.ok(new LoanResponseDto(loanService.create(loanCreateRequestDto)));

        } catch (UserNotFoundException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ErrorCode.USER_NOT_FOUND, e.getLocalizedMessage()));
        } catch (LoanCannotBeNegativeException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ErrorCode.LOAN_CANNOT_BE_NEGATIVE, e.getLocalizedMessage()));
        }
    }
}

