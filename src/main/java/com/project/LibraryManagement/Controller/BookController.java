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

    @GetMapping(value = {"/getKeywordBook/{id}"})
    public List<Book> getBookByIdList(@PathVariable String id) {
        return bookService.getSpecificBook(id);
    }

    @PostMapping(value = {"/librarian/addBook","/librarian/updateBook"}
            /*consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping(value = {"/librarian/deleteBookBy/{bookId}"})
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping(value = {"/getBookLocation/{bookId}"})
    public Location getLocationOfBookBy(@PathVariable Long bookId) {
        return bookService.getLocationOfBookBy(bookId);
    }

}
