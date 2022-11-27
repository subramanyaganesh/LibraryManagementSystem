package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.JournalArticle;
import com.project.LibraryManagement.Service.JournalArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalArticleController {

    @Autowired
    private JournalArticleService journalArticleService;

    @GetMapping(value = {"/getJournalArticle"})
    public List<JournalArticle> getJournalArticleList() {
        return journalArticleService.getAllJournalArticle();
    }

    @PostMapping(value = {"/addJournalArticle", "/updateJournalArticle"})
    public ResponseEntity<String> addJournalArticle(@RequestBody JournalArticle journalArticle) {
        return journalArticleService.createJournalArticle(journalArticle);
    }

    @DeleteMapping(value = {"/deleteJournalArticleBy/{journalArticleId}"})
    public ResponseEntity<String> deleteJournalArticle(Long journalArticleId) {
        return journalArticleService.deleteJournalArticle(journalArticleId);
    }

    @GetMapping(value = {"/getJournalBy/{journalArticleId}"})
    public Journal getEditorByJournalArticleId(@PathVariable("journalArticleId") Long id) {
        return journalArticleService.getJournalByJournalArticleId(id);
    }

    @GetMapping(value = {"/getALLAuthorsBy/{journalArticleId}"})
    public List<Author> getAuthorsByJournalArticleId(@PathVariable("journalArticleId") Long id) {
        return journalArticleService.getAllAuthorsByJournalArticleId(id);
    }

}
