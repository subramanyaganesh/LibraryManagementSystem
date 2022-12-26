package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    @Query("from Librarian where id=?1")
    Optional<Librarian> findbyid(Long id);

    @Query(value="select email_id from librarian", nativeQuery = true)
    List<String> findAllEmail();
    Optional<Librarian> findByemailId(String email);

    Optional<Librarian> findByfirstName(String firstName);

    @Query(value = "insert into member(address, email_id, first_name, last_name, password, librarian_id) values (?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Librarian> saveData(String address, String email_id, String first_name, String last_name, Long password, Long librarian_id);

}
