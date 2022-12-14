package com.project.LibraryManagement.Repository;


import com.project.LibraryManagement.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("from Book where id=?1")
    Optional<Book> findbyid(Long id);

    @Query(value = "select b from Book where title ilike", nativeQuery = true)
    Optional<Book> findbyTitle(String title);

    List<Book> findBytitleContainingIgnoreCase(String title);

    @Query(value = "insert into book (copy_number, edition, location_id, publisher_id, title, year, document_id)  values (?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Book> saveData(Long copy_number, String edition, Long location_id, Long publisher_id, String title, Date year, Long document_id);
}
