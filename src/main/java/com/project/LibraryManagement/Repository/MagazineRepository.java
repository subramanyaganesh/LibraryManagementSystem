package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    @Query("from Magazine where id=?1")
    Optional<Magazine> findbyid(Long id);
    //insert  into magazine (copy_number, location_id, magazine_name, publisher_id, document_id)  values (?, ?, ?, ?, ?)
    @Query(value = "insert  into magazine (copy_number, location_id, magazine_name, publisher_id, document_id)  values (?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Magazine> saveData(Long copy_number,Long location_id,String magazine_name,Long publisher_id,Long document_id);

}
