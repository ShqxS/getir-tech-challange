package com.kaya.orderservice.repository;

import com.kaya.orderservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByIdIn(Collection<Long> ids);
}
