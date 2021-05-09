package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.BookUpdateRequest;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookUpdateServiceTest {
  @InjectMocks private BookUpdateService bookUpdateService;
  @Mock private BookRepository bookRepository;
  @Mock private BookReadService bookReadService;

  @Captor private ArgumentCaptor<List<Book>> argumentCaptor;

  @Test
  public void shouldBatchUpdate() {
    // given
    var id = Long.valueOf(1);
    var request = new BookUpdateRequest(10);
    Map<Long, BookUpdateRequest> requestMap = new HashMap<>();
    requestMap.put(id, request);
    var books = Collections.singletonList(Book.builder().id(1L).build());
    // when
    when(bookReadService.findAllById(requestMap.keySet())).thenReturn(books);

    bookUpdateService.batchUpdate(requestMap);

    // verify
    verify(bookReadService).findAllById(requestMap.keySet());
    verify(bookRepository).saveAll(argumentCaptor.capture());
    assertEquals(books, argumentCaptor.getValue());
    assertEquals(request.getStock(), argumentCaptor.getValue().get(0).getStock());
  }
}
