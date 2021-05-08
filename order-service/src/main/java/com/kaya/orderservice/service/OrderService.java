package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderCreateService orderCreateService;
  private final OrderMapper orderMapper;

  @Transactional(rollbackFor = Exception.class)
  public OrderResponseDTO create(OrderCreateDTO orderCreateDTO) {
    return orderMapper.map(orderCreateService.create(orderCreateDTO));
  }
}
