package com.project.LibraryManagement.Service;


import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.BookRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public PublisherService publisherService;
    @Autowired
    public AuthorService authorService;

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


    public ResponseEntity<Object> createBook(Book book) {
        try {
            Set<Author> authorSet = new HashSet<>();
            if (publisherService.getPublishersByEmail(book.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(book.getPublisher());
            book.setPublisher(publisherService.getPublishersByEmail(book.getPublisher().getEmailId()).get());
            book.getAuthorSet().forEach(author -> {
                if (authorService.getAuthorByEmail(author.getEmailId()).isEmpty())
                    authorSet.add(author);
            });
            book.setAuthorSet(authorSet);
            Book result = bookRepository.save(book);
            return ResponseHandler.generateResponse("Successfully added book!", HttpStatus.CREATED, result, Book.class.getSimpleName());
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, Book.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Book.class.getSimpleName());
        }
    }

    public ResponseEntity<Object> updateBook(Book book) {
        try {
            Set<Author> authorSet = new HashSet<>();
            Book specificBook = bookRepository.findById(book.getDocument_id()).orElseThrow(() -> new Exception("Book Not Found"));
            if (publisherService.getPublishersByEmail(book.getPublisher().getEmailId()).isEmpty()) {
                publisherService.createPublisher(book.getPublisher());
            }
            specificBook.setPublisher(publisherService.getPublishersByEmail(book.getPublisher().getEmailId()).get());
            specificBook.setEdition(book.getEdition());
            specificBook.setYear(book.getYear());
            specificBook.setTitle(book.getTitle());

            book.getAuthorSet().forEach(author -> {
                if (authorService.getAuthorByEmail(author.getEmailId()).isEmpty())
                    authorSet.add(author);
            });
            specificBook.setAuthorSet(authorSet);

            specificBook.setLocation(book.getLocation());
            specificBook.setCopyNumber(book.getCopyNumber());
            Book result = bookRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully updated book!", HttpStatus.CREATED, result, "Book");
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, Book.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Book.class.getSimpleName());
        }
    }

    public ResponseEntity<Object> deleteBook(Long id) {
        try {
            var book = bookRepository.findById(id)
                    .orElseThrow(() -> new DataIntegrityViolationException("Book Not Found"));
            bookRepository.deleteById(book.getDocument_id());
            return ResponseHandler.generateResponse("Successfully Deleted Book!", HttpStatus.OK, "Success!!", "Book");
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, Librarian.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Book.class.getSimpleName());
        }
    }

    public Location getLocationOfBookBy(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return book.getLocation();
        }
        throw new IllegalStateException("The Book does not exist");
    }


}
