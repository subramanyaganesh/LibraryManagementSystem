package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.TechnicalReport;
import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalReportRepository extends JpaRepository<TechnicalReport, Long> {
}
