package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Librarian;

import com.project.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    Optional<Librarian> findByemailId(String email);
}
