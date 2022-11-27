package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
