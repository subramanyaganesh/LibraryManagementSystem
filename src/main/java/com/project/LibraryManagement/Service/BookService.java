package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Model.Writer;
import com.project.LibraryManagement.Repository.BookRepository;
import com.project.LibraryManagement.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public PublisherService publisherService;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getSpecificBook(String id) {
        List<Book> book;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            book = List.of(bookRepository.findById(i).orElse(new Book()));
        } else {
            book = bookRepository.findBytitleContainingIgnoreCase(id);
        }
        if (book != null) {
            return book;
        }
        throw new IllegalStateException("The Book does not exist");
    }


    public ResponseEntity<String> createBook(Book book) {
        if (book.getPublisher() != null && publisherService.getPublishersByEmail(book.getPublisher().getEmailId()).isEmpty())
            publisherService.createPublisher(book.getPublisher());
        bookRepository.save(book);
        return new ResponseEntity<>("Successfully added Book", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteBook(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getDocument_id());
        return new ResponseEntity<>("Successfully Deleted Book", HttpStatus.OK);
    }

    public Location getLocationOfBookBy(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return book.getLocation();
        }
        throw new IllegalStateException("The Book does not exist");
    }


}
