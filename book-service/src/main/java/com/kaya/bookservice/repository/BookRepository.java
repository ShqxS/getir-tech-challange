package com.kaya.bookservice.repository;

import com.kaya.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findById(Long id);
}
