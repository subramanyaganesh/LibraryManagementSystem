package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.JournalArticle;
import com.project.LibraryManagement.Service.JournalArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JournalArticleController {

    @Autowired
    private JournalArticleService journalArticleService;

    @GetMapping(value = {"/getJournalArticle1"})
    public  @ResponseBody List<JournalArticle> getJournalArticleList() {
        return journalArticleService.getAllJournalArticle();
    }

    @GetMapping(value = {"/getKeywordJournalArticle1/{id}"})
    public @ResponseBody  List<JournalArticle> getJournalArticleById(@PathVariable String id) {
        return journalArticleService.getSpecificJournalArticle(id);
    }

    @PostMapping(value = {"/librarian/addJournalArticle"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addJournalArticle(@RequestBody JournalArticle journalArticle) {
        return journalArticleService.createJournalArticle(journalArticle);
    }

    @PutMapping(value = {"/librarian/updateJournalArticle"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateJournalArticle(@RequestBody JournalArticle journalArticle) {
        return journalArticleService.updateJournalArticle(journalArticle);
    }

    @DeleteMapping(value = {"/librarian/deleteJournalArticleBy/{journalArticleId}"})
    public ResponseEntity<Object> deleteJournalArticle(@PathVariable Long journalArticleId) {
        return journalArticleService.deleteJournalArticle(journalArticleId);
    }

    @GetMapping(value = {"/getJournalBy1/{journalArticleId}"})
    public  @ResponseBody Journal getEditorByJournalArticleId(@PathVariable("journalArticleId") Long id) {
        return journalArticleService.getJournalByJournalArticleId(id);
    }

    @GetMapping(value = {"/getALLAuthorsBy1/{journalArticleId}"})
    public  @ResponseBody List<Author> getAuthorsByJournalArticleId(@PathVariable("journalArticleId") Long id) {
        return journalArticleService.getAllAuthorsByJournalArticleId(id);
    }

}
