package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ThesisRepository extends JpaRepository<Thesis, Long> {

}
