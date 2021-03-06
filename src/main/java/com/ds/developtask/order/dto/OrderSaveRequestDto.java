package com.ds.developtask.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderSaveRequestDto {

    private Long productId;
    private String userEmail;

    @Builder
    public OrderSaveRequestDto(Long productId, String userEmail) {
        this.productId = productId;
        this.userEmail = userEmail;
    }
}
