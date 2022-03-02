package com.ds.developtask.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.developtask.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
