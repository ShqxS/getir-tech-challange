package com.kaya.orderservice.repository;

import com.kaya.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
    extends JpaRepository<OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {}
