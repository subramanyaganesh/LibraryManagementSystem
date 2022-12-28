package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("from Publisher where id=?1")
    Optional<Publisher> findbyid(Long id);

    @Query(value="select email_id from Publisher", nativeQuery = true)
    List<String> findAllEmail();

    Optional<Publisher> findByname(String firstName);
    Optional<Publisher> findByemailId(String email);
    @Query(value = "insert  into publisher (email_id, name, publisher_id)  values (?, ?, ?)", nativeQuery = true)
    Optional<Publisher> saveData(String email_id, Long publisher_id);

}
