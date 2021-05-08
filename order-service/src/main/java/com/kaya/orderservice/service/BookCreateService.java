package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.mapper.BookMapper;
import com.kaya.orderservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCreateService {

  private final BookRepository bookRepository;
  private final BookReadService bookReadService;
  private final BookMapper bookMapper;

  public Set<Book> create(List<BookResponseDTO> booksResponse) {

    List<Book> books = booksResponse.stream().map(bookMapper::map).collect(Collectors.toList());
    List<Long> bookIds =
        booksResponse.stream().map(BookResponseDTO::getId).collect(Collectors.toList());

    List<Long> existingBooksIds =
        bookReadService.findAllById(bookIds).stream().map(Book::getId).collect(Collectors.toList());

    books =
        books.stream()
            .filter(book -> !existingBooksIds.contains(book.getId()))
            .collect(Collectors.toList());

    if (!CollectionUtils.isEmpty(books)) {
      bookRepository.saveAll(books);
    }
    return new HashSet<>(bookRepository.findAllByIdIn(bookIds));
  }
}
