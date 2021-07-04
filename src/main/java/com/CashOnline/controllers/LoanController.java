package com.CashOnline.controllers;
import com.CashOnline.dto.LoanPageDto;
import com.CashOnline.dto.LoanResponseDto;
import com.CashOnline.dto.UserCreateRequestDto;
import com.CashOnline.dto.UserResponseDto;
import com.CashOnline.exceptions.UserAlreadyExistsException;
import com.CashOnline.exceptions.UserNotFoundException;
import com.CashOnline.services.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CashOnline.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
}

