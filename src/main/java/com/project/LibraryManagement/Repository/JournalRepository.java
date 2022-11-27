package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}
