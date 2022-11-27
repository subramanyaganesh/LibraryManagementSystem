package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WriterRepository extends JpaRepository<Writer, Long> {

    Optional<Writer> findByemailId(String email);

}
