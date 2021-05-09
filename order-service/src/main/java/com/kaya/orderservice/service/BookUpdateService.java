package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.BookUpdateRequest;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookUpdateService {

  private final BookRepository bookRepository;
  private final BookReadService bookReadService;

  @Transactional(rollbackFor = Exception.class)
  public List<Book> batchUpdate(Map<Long, BookUpdateRequest> requestMap) {

    var books = bookReadService.findAllById(requestMap.keySet());
    for (Book book : books) {
      book.setStock(requestMap.get(book.getId()).getStock());
    }
    books = bookRepository.saveAll(books);
    log.info("Books are updated, books: {}", books.toString());
    return books;
  }
}
