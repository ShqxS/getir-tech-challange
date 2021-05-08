package com.kaya.bookservice.service;

import com.kaya.bookservice.entity.Book;
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
}
