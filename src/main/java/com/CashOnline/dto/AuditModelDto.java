package com.CashOnline.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditModelDto {
    protected Date createdAt;
    protected Date updatedAt;
}
