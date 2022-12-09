package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.TechnicalReport;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface TechnicalReportRepository extends JpaRepository<TechnicalReport, Long> {
    @Query("from TechnicalReport where id=?1")
    Optional<TechnicalReport> findbyid(Long id);

    @Query(value = "insert  into technical_report (category, copy_number, location_id, publisher_id, title, writer_id, year, document_id)  values (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<TechnicalReport> saveData(String category, Long copy_number, Long location_id, Long publisher_id, String title, Long writer_id, Date year, Long document_id);

}
