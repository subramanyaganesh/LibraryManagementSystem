package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    @Query("from Journal where id=?1")
    Optional<Journal> findbyid(Long id);

    List<Journal> findByjournalNameContainingIgnoreCase(String title);

    @Query(value = "insert  into journal (copy_number, journal_id, journal_name, location_id, publisher_id, document_id)  values (?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Journal> saveData(Long copy_number,Long journal_id,String journal_name,Long location_id,Long publisher_id,Long document_id);

}
