package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.MagazineRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MagazineService {
    @Autowired
    public MagazineRepository magazineRepository;
    @Autowired
    public PublisherService publisherService;
    @Autowired
    public IssuesService issuesService;
    @Autowired
    public ContributorService contributorService;

    public ResponseEntity<Object> createMagazine(Magazine magazine) {
        Set<Contributor> contributorSet = new HashSet<>();
        Set<Issues> issuesSet = new HashSet<>();
        try {
            if (publisherService.getPublishersByEmail(magazine.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(magazine.getPublisher());
            magazine.setPublisher(publisherService.getPublishersByEmail(magazine.getPublisher().getEmailId()).get());

            magazine.getIssuesSet().forEach(issues -> {
                if (issuesService.getIssueById(issues.getIssueId()).isEmpty())
                    issuesService.createIssues(issues);
                issuesSet.add(issues);
            });
            magazine.setIssuesSet(issuesSet);
            magazine.getContributorSet().forEach(contributor -> {
                if (contributorService.getContributorByEmail(contributor.getEmailId()).isEmpty())
                    contributorSet.add(contributor);
            });
            magazine.setContributorSet(contributorSet);

            Magazine result = magazineRepository.save(magazine);
            return ResponseHandler.generateResponse("Successfully updated magazine!", HttpStatus.CREATED, result, "magazine");
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, "magazine");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "magazine");
        }
    }

    public ResponseEntity<Object> updateMagazine(Magazine magazine) {
        Set<Contributor> contributorSet = new HashSet<>();
        try {
            Magazine specificBook = magazineRepository.findById(magazine.getDocument_id()).orElseThrow(()->new Exception("Magazine Not Found"));
            if (publisherService.getPublishersByEmail(magazine.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(magazine.getPublisher());
            specificBook.setPublisher(publisherService.getPublishersByEmail(magazine.getPublisher().getEmailId()).get());

            magazine.getIssuesSet().forEach(issues -> {
                if (issuesService.getIssueById(issues.getIssueId()).isEmpty())
                    issuesService.createIssues(issues);
            });
            specificBook.setIssuesSet(magazine.getIssuesSet());

            magazine.getContributorSet().forEach(contributor -> {
                if (contributorService.getContributorByEmail(contributor.getEmailId()).isEmpty())
                    contributorSet.add(contributor);
            });
            specificBook.setContributorSet(contributorSet);

            specificBook.setLocation(magazine.getLocation());
            specificBook.setCopyNumber(magazine.getCopyNumber());
            specificBook.setMagazineName(magazine.getMagazineName());
            specificBook.setDocument_id(magazine.getDocument_id());

            Magazine result = magazineRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully updated magazine!", HttpStatus.CREATED, result, "magazine");
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, "magazine");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "magazine");
        }
    }

    public ResponseEntity<Object> deleteMagazine(Long id) {
        try {
            var magazine = magazineRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("Magazine not found with ID %d", id)));

            magazineRepository.deleteById(magazine.getDocument_id());
            return ResponseHandler.generateResponse("Successfully Deleted magazine!", HttpStatus.OK, "Success!!", "magazine");
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, "magazine");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "magazine");
        }
    }

    public List<Magazine> getSpecificMagazine(String id) {
        List<Magazine> magazines;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            magazines = List.of(magazineRepository.findById(i).orElse(new Magazine()));
        } else {
            magazines = magazineRepository.findBymagazineNameContainingIgnoreCase(id);
        }
        if (magazines != null) {
            return magazines;
        }
        throw new IllegalStateException("The Magazine does not exist");
    }

    public List<Magazine> getAllMagazine() {
        return magazineRepository.findAll();
    }

    public List<Contributor> getAllContributorsByMagazineId(Long id) {
        Magazine magazine = magazineRepository.findById(id).orElse(null);
        if (magazine != null) {
            return new ArrayList<>(magazine.getContributorSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    //check This
    public List<Editor> getAllEditorsByMagazineId(Long id) {
        Magazine magazine = magazineRepository.findById(id).orElse(null);
        if (magazine != null) {
            Set<Issues> issueSet = magazine.getIssuesSet();
            return issueSet.stream().map(Issues::getEditorSet)
                    .collect(ArrayList::new, List::addAll, List::addAll);


        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Issues> getAllIssuesByMagazineId(Long id) {
        Magazine magazine = magazineRepository.findById(id).orElse(null);
        if (magazine != null) {
            return new ArrayList<>(magazine.getIssuesSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public Location getLocationOfMagazineBy(Long id) {
        Magazine magazine = magazineRepository.findById(id).orElse(null);
        if (magazine != null) {
            return magazine.getLocation();
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public Publisher getPublisherByMagazineId(Long id) {
        Magazine magazine = magazineRepository.findById(id).orElse(null);
        if (magazine != null) {
            return magazine.getPublisher();
        }
        throw new IllegalStateException("The Writer does not exist");
    }


}
