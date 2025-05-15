package com.ditto.bookmanagement.repository;

import com.ditto.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
