package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(value = {"/getAuthors"})
    public List<Author> getAuthorList() {
        return authorService.getAllAuthors();
    }

    @GetMapping(value = {"/getAllBooksByAuthorId/{authorId}"})
    public List<Book> getBooksByAuthorId(@PathVariable("authorId") String id) {
        return authorService.getAllBooksByAuthorId(id);
    }
    @GetMapping(value = {"/getAllJournalArticleBy/{authorId}"})
    public List<JournalArticle> getJournalArticleByAuthorId(@PathVariable("authorId") String id) {
        return authorService.getAllJournalsByAuthorId(id);
    }
    

}

