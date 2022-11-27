package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Issues;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuesRepository extends JpaRepository<Issues, Long> {
}
