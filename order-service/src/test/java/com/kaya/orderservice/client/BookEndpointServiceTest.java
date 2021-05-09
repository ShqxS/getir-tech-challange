package com.kaya.orderservice.client;

import com.kaya.orderservice.dto.BookQueryRequest;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.SuccessResponse;
import com.kaya.orderservice.enums.CodeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookEndpointServiceTest {

  @InjectMocks private BookEndpointService bookEndpointService;

  @Mock private BookEndpoint bookEndpoint;

  @Test
  public void shouldGetByIds() {
    // given
    var bookIds = Collections.singletonList(1L);
    var bookResponse =
        new SuccessResponse<>(
            Collections.singletonList(new BookResponseDTO()), CodeEnum.SUCCESS_RESPONSE.getCode());

    var argumentCaptor = ArgumentCaptor.forClass(BookQueryRequest.class);

    // when
    when(bookEndpoint.getByIds(any())).thenReturn(bookResponse);

    var result = bookEndpointService.getByIds(bookIds);

    verify(bookEndpoint).getByIds(argumentCaptor.capture());
    assertEquals(bookResponse.getData(), result);
  }
}
