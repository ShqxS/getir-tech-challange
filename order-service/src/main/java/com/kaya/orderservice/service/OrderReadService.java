package com.kaya.orderservice.service;

import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.entity.QOrderEntity;
import com.kaya.orderservice.enums.CodeEnum;
import com.kaya.orderservice.exception.OrderException;
import com.kaya.orderservice.repository.OrderRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderReadService {

  private final OrderRepository orderRepository;

  public List<OrderEntity> query(String username) {
    QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
    BooleanExpression usernameExpression = qOrderEntity.username.eq(username);
    return (List<OrderEntity>) orderRepository.findAll(usernameExpression);
  }

  public OrderEntity getById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(
            () -> {
              log.error("Order not found with id: {}", id);
              throw new OrderException(CodeEnum.CONTENT_NOT_FOUND_ERROR);
            });
  }
}
