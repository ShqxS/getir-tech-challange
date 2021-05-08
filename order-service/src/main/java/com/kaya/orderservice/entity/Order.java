package com.kaya.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Id @GeneratedValue private Long id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "order_book",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  private Set<Book> books;

  @Transient
  public Integer getTotalCost() {
    return books.stream().mapToInt(Book::getPrice).sum();
  }
}
