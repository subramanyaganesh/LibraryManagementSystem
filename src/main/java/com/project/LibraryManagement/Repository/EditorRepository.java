package com.project.LibraryManagement.Repository;


import com.project.LibraryManagement.Model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EditorRepository extends JpaRepository<Editor, Long> {
    @Query("from Editor where id=?1")
    Optional<Editor> findbyid(Long id);

    Optional<Editor> findByfirstName(String firstName);

    Optional<Editor> findByemailId(String email);
}
