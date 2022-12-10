package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Librarian;

import com.project.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    @Query("from Librarian where id=?1")
    Optional<Librarian> findbyid(Long id);
    Optional<Librarian> findByemailId(String email);

    Optional<Librarian> findByfirstName(String firstName);

    @Query(value = "insert into member(address, email_id, first_name, last_name, password, librarian_id) values (?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Librarian> saveData(String address, String email_id, String first_name, String last_name, Long password, Long librarian_id);

}
