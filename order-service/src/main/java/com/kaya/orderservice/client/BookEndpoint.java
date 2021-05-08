package com.kaya.orderservice.client;

import com.kaya.orderservice.dto.BookQueryRequest;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "bookClient", url = "http://localhost:9090/book/")
public interface BookEndpoint {

  @PostMapping("search")
  SuccessResponse<List<BookResponseDTO>> getByIds(
      @Validated @RequestBody BookQueryRequest bookQueryRequest);
}
