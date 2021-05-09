package com.kaya.bookservice.service;

import com.kaya.bookservice.dto.BookUpdateRequest;
import com.kaya.bookservice.entity.Book;
import com.kaya.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookUpdateService {

  private final BookRepository bookRepository;
  private final BookReadService bookReadService;

  public Book update(Long id, BookUpdateRequest bookUpdateRequest) {
    var book = bookReadService.findById(id);
    book.setStock(bookUpdateRequest.getStock());
    var user = bookRepository.save(book);
    log.info("User updated with request: {} and user: {}", bookUpdateRequest, book);
    return user;
  }
}
