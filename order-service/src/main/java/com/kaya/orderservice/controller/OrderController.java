package com.kaya.orderservice.controller;

import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.exception.CodeEnum;
import com.kaya.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrderController.ENDPOINT)
@RequiredArgsConstructor
public class OrderController {

  public static final String ENDPOINT = "order";

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<SuccessResponse<OrderResponseDTO>> create(
      @Validated @RequestBody OrderCreateDTO orderCreateDTO) {

    var response =
        new SuccessResponse<>(
            orderService.create(orderCreateDTO), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }
}
