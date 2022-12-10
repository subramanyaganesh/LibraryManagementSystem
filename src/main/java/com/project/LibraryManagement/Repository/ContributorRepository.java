package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Contributor;
import com.project.LibraryManagement.Model.Editor;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
    @Query("from Contributor where id=?1")
    Optional<Contributor> findbyid(Long id);

    Optional<Contributor> findByfirstName(String firstName);

    //
    @Query(value = "insert  into contributor (email_id, first_name, last_name, contributor_id)  values (?, ?, ?, ?)", nativeQuery = true)
    Optional<Contributor> saveData(String email_id, String first_name, String last_name, Long contributor_id);
}

