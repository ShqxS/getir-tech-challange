package com.kaya.bookservice.service;

import com.kaya.bookservice.dto.BookQueryRequest;
import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.dto.BookUpdateRequest;
import com.kaya.bookservice.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookReadService bookReadService;
  private final BookUpdateService bookUpdateService;
  private final BookMapper bookMapper;

  public List<BookResponseDTO> getAll() {
    return bookReadService.findAll().stream().map(bookMapper::map).collect(Collectors.toList());
  }

  public BookResponseDTO get(Long id) {
    return bookMapper.map(bookReadService.findById(id));
  }

  public List<BookResponseDTO> getByIds(BookQueryRequest bookQueryRequest) {
    return bookReadService.findAllByIds(bookQueryRequest.getBookIds()).stream()
        .map(bookMapper::map)
        .collect(Collectors.toList());
  }

  public BookResponseDTO update(Long id, BookUpdateRequest bookUpdateRequest) {
    return bookMapper.map(bookUpdateService.update(id, bookUpdateRequest));
  }
}
