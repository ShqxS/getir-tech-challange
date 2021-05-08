package com.kaya.orderservice.mapper;

import com.kaya.orderservice.dto.BookResponseDTO;
import com.kaya.orderservice.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book map(BookResponseDTO bookResponseDTO);

}
