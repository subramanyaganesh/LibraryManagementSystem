package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
}
