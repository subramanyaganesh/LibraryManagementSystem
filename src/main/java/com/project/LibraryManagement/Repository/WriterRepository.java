package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    @Query("from Writer where id=?1")
    Optional<Writer> findbyid(Long id);

    Optional<Writer> findByemailId(String email);

    @Query(value = "insert into writer (email_id,first_name,last_name,writer_id) values (?,?,?,?)", nativeQuery = true)
    Optional<Writer> saveData(String email_id,String first_name,String last_name,Long writer_id);

}
