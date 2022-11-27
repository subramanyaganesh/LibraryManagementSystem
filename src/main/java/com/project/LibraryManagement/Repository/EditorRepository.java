package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Editor;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<Editor, Long> {
}
