package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.JournalArticleRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JournalArticleService {
    @Autowired
    public JournalArticleRepository journalArticleRepository;
    @Autowired
    public AuthorService authorService;
    @Autowired
    public JournalService journalService;

    public List<JournalArticle> getAllJournalArticle() {
        return journalArticleRepository.findAll();
    }

    public ResponseEntity<Object> createJournalArticle(JournalArticle journalArticle) {
        Set<Author> authorSet = new HashSet<>();
        try {
            journalArticle.getAuthorSet().forEach(author -> {
                if (authorService.getAuthorByEmail(author.getEmailId()).isEmpty())
                    authorSet.add(author);
            });
            journalArticle.setAuthorSet(authorSet);
            if (journalService.getSpecificJournal(journalArticle.getJournal().getJournalName()).isEmpty())
                journalService.createJournal(journalArticle.getJournal());
            journalArticle.setJournal(journalService.getSpecificJournal(journalArticle.getJournal().getJournalName()).get(0));
            JournalArticle result = journalArticleRepository.save(journalArticle);
            return ResponseHandler.generateResponse("Successfully added JournalArticle!", HttpStatus.CREATED, result,"JournalArticle");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, JournalArticle.class.getSimpleName());
        }
    }

    public ResponseEntity<Object> updateJournalArticle(JournalArticle journalArticle) {
        Set<Author> authorSet = new HashSet<>();
        try {
            JournalArticle specificBook = journalArticleRepository.findById(journalArticle.getDocument_id()).orElseThrow(()->new Exception("Journal Article Not Found"));
            journalArticle.getAuthorSet().forEach(author -> {
                if (authorService.getAuthorByEmail(author.getEmailId()).isEmpty())
                    authorSet.add(author);
            });
            specificBook.setAuthorSet(authorSet);

            if (journalService.getSpecificJournal(journalArticle.getJournal().getJournalName()).isEmpty()){
                journalService.createJournal(journalArticle.getJournal());
            }
            specificBook.setJournal(journalService.getSpecificJournal(journalArticle.getJournal().getJournalName()).get(0));
            specificBook.setTitle(journalArticle.getTitle());
            JournalArticle result = journalArticleRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully updated JournalArticle!", HttpStatus.CREATED, result, "JournalArticle");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "JournalArticle");
        }
    }

    public List<JournalArticle> getSpecificJournalArticle(String id) {
        List<JournalArticle> journalArticle;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            journalArticle = List.of(journalArticleRepository.findById(i).orElse(new JournalArticle()));
        } else {
            journalArticle = journalArticleRepository.findBytitleContainingIgnoreCase(id);
        }
        if (journalArticle != null) {
            return journalArticle;
        }
        throw new IllegalStateException("The JournalArticle does not exist");
    }

    public ResponseEntity<Object> deleteJournalArticle(Long id) {
        try {
            var journalArticle = journalArticleRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("JournalArticle not found with ID %d", id)));

            journalArticleRepository.deleteById(journalArticle.getDocument_id());
            return ResponseHandler.generateResponse("Successfully Deleted JournalArticle !", HttpStatus.OK, "Success!!", "JournalArticle");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, JournalArticle.class.getSimpleName());
        }
    }


    public List<Author> getAllAuthorsByJournalArticleId(Long id) {
        JournalArticle journalArticle = journalArticleRepository.findById(id).orElse(null);
        if (journalArticle != null) {
            return new ArrayList<>(journalArticle.getAuthorSet());
        }
        throw new IllegalStateException("The Author does not exist");
    }

    public Journal getJournalByJournalArticleId(Long id) {
        JournalArticle journalArticle = journalArticleRepository.findById(id).orElse(null);
        if (journalArticle != null) {
            return journalArticle.getJournal();
        }
        throw new IllegalStateException("The JournalArticle does not exist");
    }

}
