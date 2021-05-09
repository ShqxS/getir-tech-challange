package com.kaya.orderservice.service;

import com.kaya.orderservice.client.BookEndpointService;
import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.entity.Book;
import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.enums.OrderStatus;
import com.kaya.orderservice.repository.OrderRepository;
import com.kaya.orderservice.validator.OrderValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderCreateServiceTest {
  @InjectMocks private OrderCreateService orderCreateService;

  @Mock private OrderRepository orderRepository;
  @Mock private OrderValidator orderValidator;
  @Mock private BookEndpointService bookEndpointService;
  @Mock private BookCreateService bookCreateService;
  @Captor private ArgumentCaptor<OrderEntity> argumentCaptor;

  @Before
  public void setupMock() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn("username");
  }

  @Test
  public void shouldCreate() {
    // given
    var createDTO = new OrderCreateDTO(Collections.singletonList(1L));
    var bookResponses =
        Collections.singletonList(BookResponseDTO.builder().id(1L).price(10).stock(20).build());
    var books = Collections.singleton(Book.builder().id(1L).price(10).stock(20).build());
    // when
    when(bookEndpointService.getByIds(createDTO.getBookIds())).thenReturn(bookResponses);
    when(bookCreateService.create(bookResponses)).thenReturn(books);

    orderCreateService.create(createDTO);
    // verify
    verify(bookEndpointService).getByIds(createDTO.getBookIds());
    verify(orderValidator)
        .validateCount(
            createDTO.getBookIds(),
            bookResponses.stream().map(BookResponseDTO::getId).collect(Collectors.toList()));
    verify(bookCreateService).create(bookResponses);
    verify(orderRepository).save(argumentCaptor.capture());
    assertEquals(OrderStatus.ACTIVE, argumentCaptor.getValue().getStatus());
    assertEquals(books, argumentCaptor.getValue().getBooks());
  }
}
