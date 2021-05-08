package com.kaya.bookservice.service;

import com.kaya.bookservice.entity.Book;
import com.kaya.bookservice.exception.BookException;
import com.kaya.bookservice.exception.CodeEnum;
import com.kaya.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookReadService {

  private final BookRepository bookRepository;

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book findById(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new BookException(CodeEnum.CONTENT_NOT_FOUND_ERROR));
  }
}
