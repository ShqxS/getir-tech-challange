package com.kaya.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

  private Long id;
  private String name;
  private Integer price;
  private Integer pageSize;
  private Integer stock;
}
