package com.kaya.orderservice.service;

import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookReadService {

  private final BookRepository bookRepository;

  public List<Book> findAllById(Collection<Long> ids) {
    return bookRepository.findAllByIdIn(ids);
  }
}
