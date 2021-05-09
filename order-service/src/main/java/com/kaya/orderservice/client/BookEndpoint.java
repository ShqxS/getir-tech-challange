package com.kaya.orderservice.client;

import com.kaya.orderservice.dto.BookQueryRequest;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "bookClient", url = "${feign.bookservice.url}")
public interface BookEndpoint {

  @PostMapping("search")
  SuccessResponse<List<BookResponseDTO>> getByIds(
      @Valid @RequestBody BookQueryRequest bookQueryRequest);
}
