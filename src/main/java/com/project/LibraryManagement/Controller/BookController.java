package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"/getBooks"})
    public List<Book> getBookList() {
        return bookService.getAllBooks();
    }
    @PostMapping(value = {"/addBook","/updateBook"})
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @DeleteMapping(value = {"/deleteBookBy/{bookId}"})
    public ResponseEntity<String> deleteBook(Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping(value = {"/getBookLocation/{bookId}"})
    public Location getLocationOfBookBy(@PathVariable Long bookId) {
        return bookService.getLocationOfBookBy(bookId);
    }

}
