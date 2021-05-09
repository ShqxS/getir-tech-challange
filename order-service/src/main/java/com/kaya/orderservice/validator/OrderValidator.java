package com.kaya.orderservice.validator;

import com.kaya.orderservice.enums.CodeEnum;
import com.kaya.orderservice.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrderValidator {

  public void validateCount(Collection<Long> ids, Collection<Long> requestIds) {
    requestIds.removeAll(ids);
    if (!CollectionUtils.isEmpty(requestIds)) {
      throw new OrderException(CodeEnum.FIELD_VALIDATION_ERROR);
    }
  }
}
