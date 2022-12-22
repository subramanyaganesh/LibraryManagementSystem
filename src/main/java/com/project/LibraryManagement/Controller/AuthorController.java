package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController for only api
//@Controller for api as well as web
@Controller
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping(value = {"/getAuthors1"})
    public @ResponseBody List<Author> getAuthorList() {
        return authorService.getAllAuthors();
    }



    @GetMapping(value = {"/getAllBooksByAuthorId1/{authorId}"})
    public @ResponseBody List<Book> getBooksByAuthorId(@PathVariable("authorId") String id) {
        return authorService.getAllBooksByAuthorId(id);
    }

    @GetMapping(value = {"/getAllBooksByAuthorId/{authorId}"})
    public String getBooksByAuthorId(@PathVariable("authorId") String id, Model model) {
        model.addAttribute("authors", authorService.getAllBooksByAuthorId(id));
        return "list-authors";
    }

    @GetMapping(value = {"/getAllJournalArticleBy1/{authorId}"})
    public @ResponseBody List<JournalArticle> getJournalArticleByAuthorId(@PathVariable("authorId") String id) {
        return authorService.getAllJournalsByAuthorId(id);
    }

    @GetMapping(value = {"/getAllJournalArticleBy/{authorId}"})
    public String getJournalArticleByAuthorId(@PathVariable("authorId") String id, Model model) {
        model.addAttribute("authors", authorService.getAllJournalsByAuthorId(id));
        return "list-authors";
    }


}

