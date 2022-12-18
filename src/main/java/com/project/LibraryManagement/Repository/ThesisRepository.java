package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    @Query("from Thesis where id=?1")
    Optional<Thesis> findbyid(Long id);
    List<Thesis> findBytitleContainingIgnoreCase(String title);
    @Query(value = "insert into thesis (category,copy_number,location_id,publisher_id,title,writer_id,year,document_id) values (?,?,?,?,?,?,?,?)", nativeQuery = true)
    Optional<Thesis> saveData(Thesis thesis);
}
