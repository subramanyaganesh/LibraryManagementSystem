package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.JournalRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JournalService {
    @Autowired
    public JournalRepository journalRepository;
    @Autowired
    public PublisherService publisherService;

    @Autowired
    public IssuesService issuesService;
    @Autowired
    public EditorService editorService;

    public List<Journal> getAllJournal() {
        return journalRepository.findAll();
    }

    public List<Journal> getSpecificJournal(String id) {
        List<Journal> journal;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            journal = List.of(journalRepository.findById(i).orElse(new Journal()));
        } else {
            journal = journalRepository.findByjournalNameContainingIgnoreCase(id);
        }
        if (journal != null) {
            return journal;
        }
        throw new IllegalStateException("The Journal does not exist");
    }

    public ResponseEntity<Object> createJournal(Journal journal) {
        try {
            if (publisherService.getPublishersByEmail(journal.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(journal.getPublisher());
            else journal.setPublisher(publisherService.getPublishersByEmail(journal.getPublisher().getEmailId()).get());
            journal.getIssuesSet().forEach(issues -> {
                if (issuesService.getIssueById(issues.getIssueId()).isEmpty())
                    issuesService.createIssues(issues);
            });
            journal.setIssuesSet(journal.getIssuesSet());
            Journal result = journalRepository.save(journal);
            return ResponseHandler.generateResponse("Successfully added Journal!", HttpStatus.CREATED, result, "JournalArticle");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, JournalArticle.class.getSimpleName());
        }
    }

    public ResponseEntity<Object> updateJournal(Journal journal) {
        try {
            Journal specificBook = journalRepository.findById(journal.getDocument_id()).orElseThrow(Exception::new);
            if (publisherService.getPublishersByEmail(journal.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(journal.getPublisher());
            specificBook.setPublisher(publisherService.getPublishersByEmail(journal.getPublisher().getEmailId()).get());

            specificBook.setJournalId(journal.getJournalId());
            specificBook.setJournalName(journal.getJournalName());
            specificBook.setJournalArticles(journal.getJournalArticles());
            specificBook.setLocation(journal.getLocation());
            specificBook.setCopyNumber(journal.getCopyNumber());

            journal.getIssuesSet().forEach(issues -> {
                if (issuesService.getIssueById(issues.getIssueId()).isEmpty()){
                    issuesService.createIssues(issues);
                }
            });
            specificBook.setIssuesSet(journal.getIssuesSet());
            Journal result = journalRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully updated Journal!", HttpStatus.CREATED, result, "JournalArticle");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, JournalArticle.class.getSimpleName());
        }
    }

    public ResponseEntity<Object> deleteJournal(Long id) {
        try {
            var journal = journalRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("Journal not found with ID %d", id)));

            journalRepository.deleteById(journal.getDocument_id());
            return ResponseHandler.generateResponse("Successfully Deleted Journal!", HttpStatus.OK, "Success!!", "Journal");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Journal.class.getSimpleName());
        }
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
