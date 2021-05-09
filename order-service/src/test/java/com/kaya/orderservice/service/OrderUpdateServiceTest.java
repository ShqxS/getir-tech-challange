package com.kaya.orderservice.service;

import com.kaya.orderservice.client.BookEndpointService;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.BookUpdateRequest;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.entity.OrderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderUpdateServiceTest {

  @InjectMocks private OrderUpdateService orderUpdateService;

  @Mock private OrderReadService orderReadService;
  @Mock private BookUpdateService bookUpdateService;
  @Mock private BookEndpointService bookEndpointService;

  @Captor private ArgumentCaptor<Map<Long, BookUpdateRequest>> argumentCaptor;

  @Test
  public void shouldUpdateStock() {
    // given
    var id = 1L;
    var book = new Book(1L, 20, 10);
    var books = Collections.singleton(book);
    var currentBooks = Collections.singletonList(new BookResponseDTO(1L, 10, 20));
    var order = OrderEntity.builder().books(books).build();

    // when
    when(orderReadService.getById(id)).thenReturn(order);
    when(bookEndpointService.getByIds(anyList())).thenReturn(currentBooks);

    orderUpdateService.updateStock(id);
    // verify
    verify(orderReadService).getById(id);
    verify(bookEndpointService)
        .getByIds(order.getBooks().stream().map(Book::getId).collect(Collectors.toList()));
    verify(bookUpdateService).batchUpdate(argumentCaptor.capture());
    assertEquals(book.getStock(), argumentCaptor.getValue().get(id).getStock());
  }
}
