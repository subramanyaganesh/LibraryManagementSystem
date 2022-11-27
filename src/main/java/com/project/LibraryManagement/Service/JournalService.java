package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JournalService {
    @Autowired
    public JournalRepository journalRepository;
    @Autowired
    public PublisherService publisherService;

    public List<Journal> getAllJournal() {
        return journalRepository.findAll();
    }

    public ResponseEntity<String> createJournal(Journal journal) {
        if (publisherService.getPublishersByEmail(journal.getPublisher().getEmailId()).isEmpty())
            publisherService.createPublisher(journal.getPublisher());
        journalRepository.save(journal);
        return new ResponseEntity<>("Successfully added Journal", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteJournal(Long id) {
        var journal = journalRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Journal not found with ID %d", id)));

        journalRepository.deleteById(journal.getDocument_id());
        return new ResponseEntity<>("Successfully Deleted Journal", HttpStatus.OK);
    }


    //check This
    public List<Editor> getAllEditorsByJournalId(Long id) {
        Journal journal = journalRepository.findById(id).orElse(null);
        if (journal != null) {
            Set<Issues> issueSet = journal.getIssuesSet();
            return issueSet.stream().map(Issues::getEditorSet)
                    .collect(ArrayList::new, List::addAll, List::addAll);


        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Issues> getAllIssuesByJournalId(Long id) {
        Journal journal = journalRepository.findById(id).orElse(null);
        if (journal != null) {
            return new ArrayList<>(journal.getIssuesSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<JournalArticle> getAllArticlesByJournalId(Long id) {
        Journal journal = journalRepository.findById(id).orElse(null);
        if (journal != null) {
            return new ArrayList<>(journal.getJournalArticles());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public Location getLocationOfJournalBy(Long id) {
        Journal journal = journalRepository.findById(id).orElse(null);
        if (journal != null) {
            return journal.getLocation();
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public Publisher getPublisherByJournalId(Long id) {
        Journal journal = journalRepository.findById(id).orElse(null);
        if (journal != null) {
            return journal.getPublisher();
        }
        throw new IllegalStateException("The Writer does not exist");
    }


}
