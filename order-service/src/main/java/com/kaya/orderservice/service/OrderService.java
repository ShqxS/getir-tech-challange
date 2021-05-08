package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderCreateService orderCreateService;
  private final OrderReadService orderReadService;
  private final OrderMapper orderMapper;

  @Transactional(rollbackFor = Exception.class)
  public OrderResponseDTO create(OrderCreateDTO orderCreateDTO) {
    return orderMapper.map(orderCreateService.create(orderCreateDTO));
  }

  public List<OrderResponseDTO> query(String username) {
    return orderReadService.query(username).stream()
        .map(orderMapper::map)
        .collect(Collectors.toList());
  }

  public OrderResponseDTO getById(Long id) {
    return orderMapper.map(orderReadService.getById(id));
  }
}
