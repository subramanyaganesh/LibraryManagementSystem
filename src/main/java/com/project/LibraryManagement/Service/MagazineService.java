package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MagazineService {
    @Autowired
    public MagazineRepository magazineRepository;
    @Autowired
    public PublisherService publisherService;
    public ResponseEntity<String> createMagazine(Magazine magazine) {
        if (publisherService.getPublishersByEmail(magazine.getPublisher().getEmailId()).isEmpty())
            publisherService.createPublisher(magazine.getPublisher());
        magazineRepository.save(magazine);
        return new ResponseEntity<>("Successfully added Magazine", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteMagazine(Long id) {
        var magazine = magazineRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Magazine not found with ID %d", id)));

        magazineRepository.deleteById(magazine.getDocument_id());
        return new ResponseEntity<>("Successfully Deleted Magazine", HttpStatus.OK);
    }

    public List<Magazine> getAllMagazine() {
        return magazineRepository.findAll();
    }

    public List<Contributor> getAllContributorsByMagazineId(Long id){
       Magazine magazine= magazineRepository.findById(id).orElse(null);
       if (magazine!=null){
           return new ArrayList<>(magazine.getContributorSet());
       }
        throw new IllegalStateException("The Writer does not exist");
    }

    //check This
    public List<Editor> getAllEditorsByMagazineId(Long id){
        Magazine magazine= magazineRepository.findById(id).orElse(null);
        if (magazine!=null){
            Set<Issues> issueSet =magazine.getIssuesSet();
          return issueSet.stream().map(Issues::getEditorSet)
                  .collect(ArrayList::new, List::addAll, List::addAll);


        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Issues> getAllIssuesByMagazineId(Long id){
        Magazine magazine= magazineRepository.findById(id).orElse(null);
        if (magazine!=null){
            return new ArrayList<>(magazine.getIssuesSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public Location getLocationOfMagazineBy(Long id){
        Magazine magazine= magazineRepository.findById(id).orElse(null);
        if (magazine!=null){
            return magazine.getLocation();
        }
        throw new IllegalStateException("The Writer does not exist");
    }
    public Publisher getPublisherByMagazineId(Long id){
        Magazine magazine= magazineRepository.findById(id).orElse(null);
        if (magazine!=null){
            return magazine.getPublisher();
        }
        throw new IllegalStateException("The Writer does not exist");
    }



}
