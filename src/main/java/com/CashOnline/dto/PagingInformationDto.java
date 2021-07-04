package com.CashOnline.dto;

import lombok.Getter;

@Getter
public class PagingInformationDto {
    Integer page;
    Integer size;
    Integer total;

    public PagingInformationDto (Integer page, Integer size, Integer total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }
}
