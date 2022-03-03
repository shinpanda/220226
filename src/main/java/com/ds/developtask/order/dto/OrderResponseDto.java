package com.ds.developtask.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponseDto {

    private Long id;
    private String userEmail;
    private Long productId;

    @Builder
    public OrderResponseDto(Long id, String userEmail, Long productId) {
        this.id = id;
        this.userEmail = userEmail;
        this.productId = productId;
    }
}
