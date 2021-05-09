package com.kaya.bookservice.service;

import com.kaya.bookservice.dto.BookUpdateRequest;
import com.kaya.bookservice.entity.Book;
import com.kaya.bookservice.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookUpdateServiceTest {

  @InjectMocks private BookUpdateService bookUpdateService;

  @Mock private BookRepository bookRepository;
  @Mock private BookReadService bookReadService;

  @Test
  public void shouldUpdate() {
    // given
    var id = 1L;
    var stock = Integer.valueOf(10);
    var request = BookUpdateRequest.builder().stock(stock).build();
    var book = new Book();
    var argumentCaptor = ArgumentCaptor.forClass(Book.class);

    // when
    when(bookReadService.findById(id)).thenReturn(book);

    bookUpdateService.update(id, request);

    // verify
    verify(bookReadService).findById(id);
    verify(bookRepository).save(argumentCaptor.capture());
    assertEquals(stock, argumentCaptor.getValue().getStock());
  }
}
