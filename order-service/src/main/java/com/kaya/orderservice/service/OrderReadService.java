package com.kaya.orderservice.service;

import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.entity.QOrderEntity;
import com.kaya.orderservice.repository.OrderRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderReadService {

  private final OrderRepository orderRepository;

  public List<OrderEntity> query(String username) {
    QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
    BooleanExpression usernameExpression = qOrderEntity.username.eq(username);
    return (List<OrderEntity>) orderRepository.findAll(usernameExpression);
  }
}
