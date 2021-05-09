package com.kaya.orderservice.service;

import com.kaya.orderservice.dto.OrderCreateDTO;
import com.kaya.orderservice.dto.OrderResponseDTO;
import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.mapper.OrderMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
  @InjectMocks private OrderService orderService;
  @Mock private OrderCreateService orderCreateService;
  @Mock private OrderReadService orderReadService;
  @Mock private OrderUpdateService orderUpdateService;
  @Mock private OrderMapper orderMapper;

  @Before
  public void setupMock() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .thenReturn("username");
  }

  @Test
  public void shouldCreate() {
    // given
    var orderCreateDTO = new OrderCreateDTO();
    var order = new OrderEntity();

    // when
    when(orderCreateService.create(orderCreateDTO)).thenReturn(order);

    orderService.create(orderCreateDTO);
    // verify
    verify(orderCreateService).create(orderCreateDTO);
  }

  @Test
  public void shouldQuery() {
    // when
    var orderResponseDto = new OrderResponseDTO();
    var orders = Collections.singletonList(new OrderEntity());

    when(orderMapper.map(any())).thenReturn(orderResponseDto);
    when(orderReadService.query(anyString())).thenReturn(orders);

    var result = orderService.query();
    // verify
    verify(orderReadService).query("username");
    assertEquals(Collections.singletonList(orderResponseDto), result);
  }

  @Test
  public void shouldGetById() {
    // given
    var id = 1L;
    var orderResponseDto = new OrderResponseDTO();
    var order = new OrderEntity();

    // when
    when(orderReadService.getById(anyLong())).thenReturn(order);
    when(orderMapper.map(order)).thenReturn(orderResponseDto);

    var result = orderService.getById(id);
    // verify
    verify(orderReadService).getById(id);
    verify(orderMapper).map(order);
  }

  @Test
  public void shouldUpdateStock() {
    // given
    var id = 1L;
    var orderResponseDto = new OrderResponseDTO();
    var order = new OrderEntity();

    // when
    when(orderUpdateService.updateStock(id)).thenReturn(order);
    when(orderMapper.map(order)).thenReturn(orderResponseDto);

    var result = orderService.updateStock(id);
    // verify
    verify(orderUpdateService).updateStock(id);
    verify(orderMapper).map(order);
  }
}
