package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Editor;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EditorRepository extends JpaRepository<Editor, Long> {
    @Query("from Editor where id=?1")
    Optional<Editor> findbyid(Long id);
}
