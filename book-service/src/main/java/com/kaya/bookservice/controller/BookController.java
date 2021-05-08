package com.kaya.bookservice.controller;

import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.dto.SuccessResponse;
import com.kaya.bookservice.exception.CodeEnum;
import com.kaya.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    var response = new SuccessResponse<>(bookService.getAll(), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<SuccessResponse<BookResponseDTO>> get(@PathVariable("id") Long id) {
    var response = new SuccessResponse<>(bookService.get(id), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }
}
