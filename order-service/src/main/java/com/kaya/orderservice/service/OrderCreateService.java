package com.kaya.orderservice.service;

import com.kaya.orderservice.client.BookEndpointService;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.repository.OrderRepository;
import com.kaya.orderservice.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

  private final OrderRepository orderRepository;
  private final OrderValidator orderValidator;
  private final BookEndpointService bookEndpointService;
  private final BookCreateService bookCreateService;

  public OrderEntity create(OrderCreateDTO orderCreateDTO) {
    var booksResponse = bookEndpointService.getByIds(orderCreateDTO.getBookIds());
    orderValidator.validateCount(
        orderCreateDTO.getBookIds(),
        booksResponse.stream().map(BookResponseDTO::getId).collect(Collectors.toList()));

    Set<Book> books = bookCreateService.create(booksResponse);

    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setUsername(orderCreateDTO.getUsername());
    orderEntity.setBooks(books);

    orderRepository.save(orderEntity);

    return orderEntity;
  }
}
