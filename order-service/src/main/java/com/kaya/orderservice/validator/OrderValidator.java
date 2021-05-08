package com.kaya.orderservice.validator;

import com.kaya.orderservice.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrderValidator {

  public void validateCount(Collection<Long> ids, Collection<Long> requestIds) {


  }
}
