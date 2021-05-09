package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.mapper.BookMapper;
import com.kaya.orderservice.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookCreateServiceTest {

  @InjectMocks private BookCreateService bookCreateService;

  @Mock private BookRepository bookRepository;
  @Mock private BookReadService bookReadService;
  @Mock private BookMapper bookMapper;

  @Test
  public void shouldCreateWhenNoBookExist() {
    // given
    var book = new Book();
    var bookResponse = BookResponseDTO.builder().id(1L).build();
    var bookResponses = Collections.singletonList(bookResponse);

    // when
    when(bookMapper.map(any())).thenReturn(book);
    when(bookReadService.findAllById(anyCollection())).thenReturn(new ArrayList<>());

    bookCreateService.create(bookResponses);

    verify(bookRepository).saveAll(Collections.singletonList(book));
    verify(bookReadService).findAllById(Collections.singletonList(bookResponse.getId()));
    verify(bookRepository).findAllByIdIn(Collections.singletonList(bookResponse.getId()));
  }

  @Test
  public void shouldNotCreateWhenBooksAlreadyExist() {
    // given
    var book = new Book();
    var bookResponse = BookResponseDTO.builder().id(1L).build();
    var bookResponses = Collections.singletonList(bookResponse);

    // when
    when(bookMapper.map(any())).thenReturn(book);
    when(bookReadService.findAllById(anyCollection())).thenReturn(Collections.singletonList(book));

    bookCreateService.create(bookResponses);

    verify(bookReadService).findAllById(Collections.singletonList(bookResponse.getId()));
    verify(bookRepository).findAllByIdIn(Collections.singletonList(bookResponse.getId()));
  }
}
