package com.kaya.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  @Id private Long id;

  @Column(nullable = false)
  private Integer stock;

  @Column(nullable = false)
  private Integer price;
}
