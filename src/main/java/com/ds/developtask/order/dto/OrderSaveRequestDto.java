package com.ds.developtask.order.dto;

import com.ds.developtask.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderSaveRequestDto {

    private Long productId;
    private String orderedUserEmail;

    @Builder
    public OrderSaveRequestDto(Long productId, String orderedUserEmail) {
        this.productId = productId;
        this.orderedUserEmail = orderedUserEmail;
    }
}
