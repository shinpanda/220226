package com.ds.developtask.order.dto;

import com.ds.developtask.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserOrdersDto {

    private Long orderId;
    private Long userId;
    private Product product;

    @Builder
    public UserOrdersDto(Long orderId, Long userId, Product product) {
        this.orderId = orderId;
        this.userId = userId;
        this.product = product;
    }
}
