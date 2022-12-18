package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.JournalArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JournalArticleRepository extends JpaRepository<JournalArticle, Long> {
    @Query("from JournalArticle where id=?1")
    Optional<JournalArticle> findbyid(Long id);

    List<JournalArticle> findBytitleContainingIgnoreCase(String title);
}

