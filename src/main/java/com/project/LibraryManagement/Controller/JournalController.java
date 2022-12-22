package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping(value = {"/getJournal1"})
    public  @ResponseBody List<Journal> getJournalList() {
        return journalService.getAllJournal();
    }
    @GetMapping(value = {"/getKeywordJournal1/{id}"})
    public  @ResponseBody List<Journal> getJournalById(@PathVariable String id) {
        return journalService.getSpecificJournal(id);
    }
    @PostMapping(value = {"/librarian/addJournal"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addJournal(@RequestBody Journal journal) {
        return journalService.createJournal(journal);
    }
    @PutMapping(value = { "/librarian/updateJournal"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateJournal(@RequestBody Journal journal) {
        return journalService.updateJournal(journal);
    }

    @DeleteMapping(value = {"/librarian/deleteJournalBy/{JournalId}"})
    public ResponseEntity<Object> deleteJournal(@PathVariable Long JournalId) {
        return journalService.deleteJournal(JournalId);
    }

    @GetMapping(value = {"/getAllEditorsBy1/{JournalId}"})
    public @ResponseBody  List<Editor> getEditorByMagazineId(@PathVariable("JournalId") Long id) {
        return journalService.getAllEditorsByJournalId(id);
    }

    @GetMapping(value = {"/getAllArticlesBy1/{JournalId}"})
    public  @ResponseBody List<JournalArticle> getArticlesByMagazineId(@PathVariable("JournalId") Long id) {
        return journalService.getAllArticlesByJournalId(id);
    }

    @GetMapping(value = {"/getAllIssuesBy1/{JournalId}"})
    public  @ResponseBody List<Issues> getIssuesByMagazineId(@PathVariable("JournalId") Long id) {
        return journalService.getAllIssuesByJournalId(id);
    }

    @GetMapping(value = {"/getJournalLocation1/{journalId}"})
    public  @ResponseBody Location getLocationOfJournalBy(@PathVariable Long journalId) {
        return journalService.getLocationOfJournalBy(journalId);
    }

    @GetMapping(value = {"/getPublisherByJournalId1/{JournalId}"})
    public  @ResponseBody Publisher getPublisherByJournalId(@PathVariable("JournalId") Long id) {
        return journalService.getPublisherByJournalId(id);
    }

}
