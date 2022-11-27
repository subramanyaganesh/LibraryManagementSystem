package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.JournalArticle;
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

    public List<Book> getAllBooksByAuthorId(Long id){
       Author author= authorRepository.findById(id).orElse(null);
       if (author!=null){
           return new ArrayList<>(author.getBooks());
       }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<JournalArticle> getAllJournalsByAuthorId(Long id){
        Author author= authorRepository.findById(id).orElse(null);
        if (author!=null){
            return new ArrayList<>(author.getJournalArticles());
        }
        throw new IllegalStateException("The Writer does not exist");
    }
}
