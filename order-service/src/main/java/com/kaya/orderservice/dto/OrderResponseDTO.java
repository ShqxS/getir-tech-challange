package com.kaya.orderservice.dto;

import com.kaya.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

  private Long id;
  private Integer totalCost;
  private String username;
  private OrderStatus status;
  private List<BookResponseDTO> books;
}
