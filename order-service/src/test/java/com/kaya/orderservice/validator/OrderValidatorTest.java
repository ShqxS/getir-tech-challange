package com.kaya.orderservice.validator;

import com.kaya.orderservice.exception.OrderException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderValidatorTest {
  @InjectMocks private OrderValidator orderValidator;

  @Test
  public void shouldValidate() {
    // given
    var ids = new ArrayList<>(List.of(1L, 2L, 3L));
    var requestIds = new ArrayList<>(List.of(1L, 2L, 3L));

    orderValidator.validateCount(ids, requestIds);
  }

  @Test(expected = OrderException.class)
  public void shouldNotValidateWhenCountNotMatched() {
    // given
    var ids = new ArrayList<>(List.of(1L, 2L, 3L));
    var requestIds = new ArrayList<>(List.of(1L, 2L, 3L, 4L));

    orderValidator.validateCount(ids, requestIds);
  }
}
