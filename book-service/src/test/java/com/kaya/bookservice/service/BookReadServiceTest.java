package com.kaya.bookservice.service;

import com.kaya.bookservice.entity.Book;
import com.kaya.bookservice.exception.BookException;
import com.kaya.bookservice.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookReadServiceTest {

  @InjectMocks private BookReadService bookReadService;

  @Mock private BookRepository bookRepository;

  @Test
  public void shouldFindAll() {
    var books = Collections.singletonList(new Book());

    when(bookRepository.findAll()).thenReturn(books);

    var result = bookReadService.findAll();

    verify(bookRepository).findAll();
    assertEquals(books, result);
  }

  @Test
  public void shouldFindById() {
    var id = 1L;
    var book = Book.builder().id(id).build();

    when(bookRepository.findById(id)).thenReturn(Optional.of(book));

    var result = bookReadService.findById(id);

    verify(bookRepository).findById(id);
    assertEquals(book, result);
  }

  @Test(expected = BookException.class)
  public void shouldNotFindByIdWhenBookNotExists() {
    var id = 1L;
    when(bookRepository.findById(id)).thenReturn(Optional.empty());
    bookReadService.findById(id);
  }

  @Test
  public void shouldFindAllByIds() {
    var ids = Collections.singletonList(1L);
    var books = Collections.singletonList(new Book());

    when(bookRepository.findAllByIdIn(ids)).thenReturn(books);
    var result = bookReadService.findAllByIds(ids);

    verify(bookRepository).findAllByIdIn(ids);
    assertEquals(books, result);
  }
}
