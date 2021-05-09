package com.kaya.orderservice.service;

import com.kaya.orderservice.entity.OrderEntity;
import com.kaya.orderservice.entity.QOrderEntity;
import com.kaya.orderservice.exception.OrderException;
import com.kaya.orderservice.repository.OrderRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReadServiceTest {
  @InjectMocks private OrderReadService orderReadService;

  @Mock private OrderRepository orderRepository;


  @Test
  public void shouldQuery() {
    // given
    var username = "username";
    QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
    BooleanExpression usernameExpression = qOrderEntity.username.eq(username);

    orderReadService.query(username);
    // verify
    verify(orderRepository).findAll(usernameExpression);
  }

  @Test
  public void shouldGetById() {
    // given
    var id = 1L;
    var order = new OrderEntity();

    // when
    when(orderRepository.findById(id)).thenReturn(Optional.of(order));

    var result = orderReadService.getById(id);
    // verify
    verify(orderRepository).findById(id);
    assertEquals(order, result);
  }

  @Test(expected = OrderException.class)
  public void shouldNotGetById() {
    // given
    var id = 1L;

    // when
    when(orderRepository.findById(id)).thenReturn(Optional.empty());
    orderReadService.getById(id);
  }
}
