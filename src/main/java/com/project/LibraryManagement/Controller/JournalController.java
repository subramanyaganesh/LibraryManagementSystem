package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping(value = {"/getJournal"})
    public List<Journal> getJournalList() {
        return journalService.getAllJournal();
    }
    @GetMapping(value = {"/getKeywordJournal/{id}"})
    public List<Journal> getJournalById(@PathVariable String id) {
        return journalService.getSpecificJournal(id);
    }
    @PostMapping(value = {"/librarian/addJournal", "/librarian/updateJournal"})
    public ResponseEntity<String> addJournal(@RequestBody Journal journal) {
        return journalService.createJournal(journal);
    }

    @DeleteMapping(value = {"/librarian/deleteJournalBy/{JournalId}"})
    public ResponseEntity<String> deleteJournal(@PathVariable Long JournalId) {
        return journalService.deleteJournal(JournalId);
    }

    @GetMapping(value = {"/getAllEditorsBy/{JournalId}"})
    public List<Editor> getEditorByMagazineId(@PathVariable("JournalId") Long id) {
        return journalService.getAllEditorsByJournalId(id);
    }

    @GetMapping(value = {"/getAllArticlesBy/{JournalId}"})
    public List<JournalArticle> getArticlesByMagazineId(@PathVariable("JournalId") Long id) {
        return journalService.getAllArticlesByJournalId(id);
    }

    @GetMapping(value = {"/getAllIssuesBy/{JournalId}"})
    public List<Issues> getIssuesByMagazineId(@PathVariable("JournalId") Long id) {
        return journalService.getAllIssuesByJournalId(id);
    }

    @GetMapping(value = {"/getJournalLocation/{journalId}"})
    public Location getLocationOfJournalBy(@PathVariable Long journalId) {
        return journalService.getLocationOfJournalBy(journalId);
    }

    @GetMapping(value = {"/getPublisherByJournalId/{JournalId}"})
    public Publisher getPublisherByJournalId(@PathVariable("JournalId") Long id) {
        return journalService.getPublisherByJournalId(id);
    }

}
