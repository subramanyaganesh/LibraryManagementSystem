package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Issues;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface IssuesRepository extends JpaRepository<Issues, Long> {
    @Query("from Issues where id=?1")
    Optional<Issues> findbyid(Long id);

    @Query(value = "insert  into issues (publish_date, issue_id)  values (?, ?)", nativeQuery = true)
    Optional<Issues> saveData(Date publish_date,Long issue_id);

}
