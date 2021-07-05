package com.CashOnline.dto;

import com.CashOnline.model.Loan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanResponseDto extends AuditModelDto {
    private Long id;
    private Double total;
    private Long userId;

    public LoanResponseDto(Loan loan) {
        this.id = loan.getId();
        this.total = loan.getTotal();
        this.userId = loan.getUser().getId();
        this.createdAt = loan.getCreated();
        this.updatedAt = loan.getUpdated();
    }
}
