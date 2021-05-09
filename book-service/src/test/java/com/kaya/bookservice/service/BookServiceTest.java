package com.kaya.bookservice.service;

import com.kaya.bookservice.dto.BookQueryRequest;
import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.dto.BookUpdateRequest;
import com.kaya.bookservice.entity.Book;
import com.kaya.bookservice.mapper.BookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

  @InjectMocks private BookService bookService;

  @Mock private BookReadService bookReadService;
  @Mock private BookUpdateService bookUpdateService;
  @Mock private BookMapper bookMapper;

  @Test
  public void shouldGetAll() {
    var book = new Book();
    var bookResponse = new BookResponseDTO();

    when(bookMapper.map(any())).thenReturn(bookResponse);
    when(bookReadService.findAll()).thenReturn(Collections.singletonList(book));

    var result = bookService.getAll();

    // verify
    verify(bookReadService).findAll();
    assertEquals(Collections.singletonList(bookResponse), result);
  }

  @Test
  public void shouldGet() {
    // given
    var id = 1L;
    var book = new Book();
    var bookResponse = new BookResponseDTO();

    // when
    when(bookReadService.findById(id)).thenReturn(book);
    when(bookMapper.map(book)).thenReturn(bookResponse);

    bookService.get(id);
    // verify
    verify(bookReadService).findById(id);
    verify(bookMapper).map(book);
  }

  @Test
  public void shouldGetByIds() {
    var bookIds = Collections.singletonList(1L);
    var request = new BookQueryRequest(bookIds);
    var book = new Book();
    var bookResponse = new BookResponseDTO();

    // when
    when(bookReadService.findAllByIds(bookIds)).thenReturn(Collections.singletonList(book));
    when(bookMapper.map(any())).thenReturn(bookResponse);

    bookService.getByIds(request);

    // verify
    verify(bookReadService).findAllByIds(bookIds);
    verify(bookMapper, times(1)).map(book);
  }

  @Test
  public void shouldUpdate() {
    // given
    var id = 1L;
    var request = new BookUpdateRequest(10);
    var book = new Book();
    var bookResponse = new BookResponseDTO();

    // when
    when(bookUpdateService.update(id, request)).thenReturn(book);
    when(bookMapper.map(any())).thenReturn(bookResponse);

    bookService.update(id, request);

    // verify
    verify(bookUpdateService).update(id, request);
    verify(bookMapper).map(book);
  }
}
