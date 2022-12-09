package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("from Book where id=?1")
    Optional<Book> findbyid(Long id);

    //
    @Query(value = "insert  into book (copy_number, edition, location_id, publisher_id, title, year, document_id)  values (?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Book> saveData(Long copy_number, String edition, Long location_id, Long publisher_id, String title, Date year, Long document_id);
}
