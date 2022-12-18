package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("from Location where id=?1")
    Optional<Location> findbyid(Long id);
    //

    @Query(value = "insert  into location (level, shelf_number, room_number)  values (?, ?, ?)", nativeQuery = true)
    Optional<Location> saveData(Long level,Long shelf_number,Long room_number);

}
