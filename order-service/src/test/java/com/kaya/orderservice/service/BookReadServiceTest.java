package com.kaya.orderservice.service;

import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookReadServiceTest {
  @InjectMocks private BookReadService bookReadService;

  @Mock private BookRepository bookRepository;

  @Test
  public void shouldFindAllByIds() {
    // given
    var ids = Collections.singletonList(1L);
    var books = Collections.singletonList(new Book());

    // when
    when(bookRepository.findAllByIdIn(ids)).thenReturn(books);

    var result = bookReadService.findAllById(ids);

    // verify
    verify(bookRepository).findAllByIdIn(ids);
    assertEquals(books, result);
  }
}
