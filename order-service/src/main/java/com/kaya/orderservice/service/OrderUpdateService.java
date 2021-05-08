package com.kaya.orderservice.service;

import com.kaya.orderservice.client.BookEndpointService;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.BookUpdateRequest;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderUpdateService {

  private final OrderReadService orderReadService;
  private final BookUpdateService bookUpdateService;
  private final BookEndpointService bookEndpointService;

  public OrderEntity updateStock(Long id) {

    var order = orderReadService.getById(id);
    var currentBooks =
        bookEndpointService.getByIds(
            order.getBooks().stream().map(Book::getId).collect(Collectors.toList()));

    Map<Long, BookUpdateRequest> request =
        currentBooks.stream()
            .collect(
                Collectors.toMap(
                    BookResponseDTO::getId,
                    book -> BookUpdateRequest.builder().stock(book.getStock()).build()));

    bookUpdateService.batchUpdate(request);
    return order;
  }
}
