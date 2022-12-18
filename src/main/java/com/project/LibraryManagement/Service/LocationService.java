package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    public LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public ResponseEntity<String> createLocation(Location location) {
        locationRepository.save(location);
        return new ResponseEntity<>("Successfully added Location", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteLocation(Long id) {
        var location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Location not found with ID %d", id)));

        locationRepository.deleteById(location.getRoomNumber());
        return new ResponseEntity<>("Successfully Deleted Location", HttpStatus.OK);
    }

}
