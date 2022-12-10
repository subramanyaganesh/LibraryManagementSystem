package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.JournalArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class JournalArticleService {
    @Autowired
    public JournalArticleRepository journalArticleRepository;

    public List<JournalArticle> getAllJournalArticle() {
        return journalArticleRepository.findAll();
    }

    public ResponseEntity<String> createJournalArticle(JournalArticle journalArticle) {
        journalArticleRepository.save(journalArticle);
        return new ResponseEntity<>("Successfully added JournalArticle", HttpStatus.OK);
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
    public ResponseEntity<String> deleteJournalArticle(Long id) {
        var journalArticle = journalArticleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("JournalArticle not found with ID %d", id)));

        journalArticleRepository.deleteById(journalArticle.getDocument_id());
        return new ResponseEntity<>("Successfully Deleted JournalArticle", HttpStatus.OK);
    }


    public List<Author> getAllAuthorsByJournalArticleId(Long id){
       JournalArticle journalArticle= journalArticleRepository.findById(id).orElse(null);
       if (journalArticle!=null){
           return new ArrayList<>(journalArticle.getAuthorSet());
       }
        throw new IllegalStateException("The Author does not exist");
    }
    public Journal getJournalByJournalArticleId(Long id){
        JournalArticle journalArticle= journalArticleRepository.findById(id).orElse(null);
        if (journalArticle!=null){
            return journalArticle.getJournal();
        }
        throw new IllegalStateException("The JournalArticle does not exist");
    }

}
