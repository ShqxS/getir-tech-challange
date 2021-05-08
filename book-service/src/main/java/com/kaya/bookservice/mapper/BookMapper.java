package com.kaya.bookservice.mapper;

import com.kaya.bookservice.dto.BookResponseDTO;
import com.kaya.bookservice.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
  BookResponseDTO map(Book book);
}
