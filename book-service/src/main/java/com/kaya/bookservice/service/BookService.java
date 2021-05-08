package com.kaya.bookservice.service;

import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookReadService bookReadService;
  private final BookMapper bookMapper;

  public List<BookResponseDTO> getAll() {
    return bookReadService.findAll().stream().map(bookMapper::map).collect(Collectors.toList());
  }

  public BookResponseDTO get(Long id) {
    return bookMapper.map(bookReadService.findById(id));
  }
}
