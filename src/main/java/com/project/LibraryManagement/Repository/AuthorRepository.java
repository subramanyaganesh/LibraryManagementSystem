package com.project.LibraryManagement.Repository;


import com.project.LibraryManagement.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
//here we don't have to add @Repository annotation as JpaRepository has an implementation by simpleJpaRepository which has @Repository
//this is the default implementation class and has all its methods as transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("from Author where emailId=?1")
    Optional<Author> findByemailId(String email);

    @Query("from Author where id=?1")
    Optional<Author> findbyid(Long id);

    Optional<Author> findByfirstName(String firstName);

    @Query(value = "insert  into author (email_id, first_name, last_name, author_id)  values (?, ?, ?, ?)", nativeQuery = true)
    Optional<Author> saveData(String email_id,String first_name,String last_name,Long author_id);

}
