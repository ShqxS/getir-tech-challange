package com.kaya.orderservice.controller;

import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.dto.SuccessResponse;
import com.kaya.orderservice.enums.CodeEnum;
import com.kaya.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookController.ENDPOINT)
@RequiredArgsConstructor
public class BookController {

  public static final String ENDPOINT = "book";

  private final OrderService orderService;

  @PutMapping("{id}")
  public ResponseEntity<SuccessResponse<OrderResponseDTO>> syncStock(
      @PathVariable("id") Long id) {
    var response =
        new SuccessResponse<>(orderService.updateStock(id), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }
}
