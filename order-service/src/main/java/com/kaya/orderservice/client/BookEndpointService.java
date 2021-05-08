package com.kaya.orderservice.client;

import com.kaya.orderservice.dto.BookQueryRequest;
import com.kaya.orderservice.dto.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookEndpointService {

  private final BookEndpoint bookEndpoint;

  public List<BookResponseDTO> getByIds(List<Long> bookIds) {
    BookQueryRequest request = BookQueryRequest.builder().bookIds(bookIds).build();
    return bookEndpoint.getByIds(request).getData();
  }
}
