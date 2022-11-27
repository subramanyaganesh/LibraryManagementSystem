package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.JournalArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalArticleRepository extends JpaRepository<JournalArticle, Long> {
}
