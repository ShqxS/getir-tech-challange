package com.kaya.orderservice.controller;

import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.dto.SuccessResponse;
import com.kaya.orderservice.enums.CodeEnum;
import com.kaya.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(OrderController.ENDPOINT)
@RequiredArgsConstructor
public class OrderController {

  public static final String ENDPOINT = "order";

  private final OrderService orderService;

  @PostMapping
  @PreAuthorize("hasAuthority('WRITE_ORDER')")
  public ResponseEntity<SuccessResponse<OrderResponseDTO>> create(
      @Valid @RequestBody OrderCreateDTO orderCreateDTO) {

    var response =
        new SuccessResponse<>(
            orderService.create(orderCreateDTO), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }

  @GetMapping("query")
  @PreAuthorize("hasAuthority('READ_ORDER')")
  public ResponseEntity<SuccessResponse<List<OrderResponseDTO>>> query() {

    var response = new SuccessResponse<>(orderService.query(), CodeEnum.CONTENT_CREATED.getCode());
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAuthority('READ_ORDER')")
  public ResponseEntity<SuccessResponse<OrderResponseDTO>> getById(@PathVariable("id") Long id) {
    var response =
        new SuccessResponse<>(orderService.getById(id), CodeEnum.SUCCESS_RESPONSE.getCode());
    return ResponseEntity.ok(response);
  }
}
