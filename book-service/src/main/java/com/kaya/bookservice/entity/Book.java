package com.kaya.bookservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  private Integer pageSize;

  @Column(nullable = false)
  private Integer stock;
}
