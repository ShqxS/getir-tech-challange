package com.kaya.bookservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDTO {

  private Long id;
  private String name;
  private Integer price;
  private Integer pageSize;
  private Integer stock;
}
