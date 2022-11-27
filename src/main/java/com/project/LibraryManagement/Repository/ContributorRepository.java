package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Contributor;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
}
