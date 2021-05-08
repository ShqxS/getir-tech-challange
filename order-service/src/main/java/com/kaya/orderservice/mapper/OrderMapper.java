package com.kaya.orderservice.mapper;

import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderResponseDTO map(OrderEntity orderEntity);
}
