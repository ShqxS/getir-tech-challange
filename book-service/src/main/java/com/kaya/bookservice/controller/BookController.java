package com.kaya.bookservice.controller;

import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.dto.SuccessResponse;
import com.kaya.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BookController.ENDPOINT)
@RequiredArgsConstructor
public class BookController {

  public static final String ENDPOINT = "book";

  private final BookService bookService;

  @GetMapping
  public ResponseEntity<SuccessResponse<List<BookResponseDTO>>> getAll() {
    var response = new SuccessResponse<>(bookService.getAll(), HttpStatus.OK.value());
    return ResponseEntity.ok(response);
  }
}
