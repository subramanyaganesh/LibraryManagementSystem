package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Issues;
import com.project.LibraryManagement.Model.Issues;
import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Repository.IssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class IssuesService {
    @Autowired
    public IssuesRepository issuesRepository;

    public List<Issues> getAllIssues() {
        return issuesRepository.findAll();
    }

    public List<Magazine> getAllMagazinesByIssuesId(Long id) {
        Issues Issues = issuesRepository.findById(id).orElse(null);
        if (Issues != null) {
            return new ArrayList<>(Issues.getMagazines());
        }
        throw new IllegalStateException("The Issues does not exist");
    }

    public List<Journal> getAllJournalsByIssuesId(Long id) {
        Issues Issues = issuesRepository.findById(id).orElse(null);
        if (Issues != null) {
            return new ArrayList<>(Issues.getJournals());
        }
        throw new IllegalStateException("The Issues does not exist");
    }

    public ResponseEntity<String> createIssues(Issues Issues) {
        issuesRepository.save(Issues);
        return new ResponseEntity<>("Successfully added Issues", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteIssues(Long id) {
        var Issues = issuesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Issues not found with ID %d", id)));

        issuesRepository.deleteById(Issues.getIssueId());
        return new ResponseEntity<>("Successfully Deleted Issues", HttpStatus.OK);
    }
}
