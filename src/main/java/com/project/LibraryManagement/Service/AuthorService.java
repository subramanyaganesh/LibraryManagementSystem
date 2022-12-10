package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    public AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Book> getAllBooksByAuthorId(String id) {
        Author author;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            author = authorRepository.findbyid(i).orElse(null);
        } else {
            author = authorRepository.findByfirstName(id).orElse(null);
        }
        if (author != null) {
            return new ArrayList<>(author.getBooks());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<JournalArticle> getAllJournalsByAuthorId(String id) {
        Author author;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            author = authorRepository.findbyid(i).orElse(null);
        } else {
            author = authorRepository.findByfirstName(id).orElse(null);
        }
        if (author != null) {
            return new ArrayList<>(author.getJournalArticles());
        }
        throw new IllegalStateException("The Writer does not exist");
    }
}
