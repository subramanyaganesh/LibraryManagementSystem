package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Publisher;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("from Publisher where id=?1")
    Optional<Publisher> findbyid(Long id);
    Optional<Publisher> findByemailId(String email);
    @Query(value = "insert  into publisher (email_id, name, publisher_id)  values (?, ?, ?)", nativeQuery = true)
    Optional<Publisher> saveData(String email_id, Long publisher_id);

}
