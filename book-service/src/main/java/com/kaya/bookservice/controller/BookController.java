package com.kaya.bookservice.controller;

import com.kaya.bookservice.dto.BookQueryRequest;
import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.dto.BookUpdateRequest;
import com.kaya.bookservice.dto.SuccessResponse;
import com.kaya.bookservice.exception.CodeEnum;
import com.kaya.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  @PreAuthorize("hasAuthority('READ_BOOK')")
  public ResponseEntity<SuccessResponse<List<BookResponseDTO>>> getAll() {
    var response = new SuccessResponse<>(bookService.getAll(), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAuthority('READ_BOOK')")
  public ResponseEntity<SuccessResponse<BookResponseDTO>> get(@PathVariable("id") Long id) {
    var response = new SuccessResponse<>(bookService.get(id), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }

  @PostMapping("search")
  @PreAuthorize("hasAuthority('READ_BOOK')")
  public ResponseEntity<SuccessResponse<List<BookResponseDTO>>> getByIds(
      @Validated @RequestBody BookQueryRequest bookQueryRequest) {
    var response =
        new SuccessResponse<>(
            bookService.getByIds(bookQueryRequest), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }

  @PutMapping("{id}")
  @PreAuthorize("hasAuthority('WRITE_BOOK')")
  public ResponseEntity<SuccessResponse<BookResponseDTO>> update(
      @PathVariable("id") Long id, @Validated @RequestBody BookUpdateRequest bookUpdateRequest) {
    var response =
        new SuccessResponse<>(
            bookService.update(id, bookUpdateRequest), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }
}
