package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
