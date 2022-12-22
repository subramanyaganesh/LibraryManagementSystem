package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"/getBooks1"})
    public @ResponseBody List<Book> getBookList() {
        return bookService.getAllBooks();
    }



    @GetMapping(value = {"/getKeywordBook1/{id}"})
    public  @ResponseBody List<Book> getBookByIdList(@PathVariable String id) {
        return bookService.getSpecificBook(id);
    }

    @PostMapping(value = {"/librarian/addBook"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }


    @PutMapping(value = {"/librarian/updateBook"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping(value = {"/librarian/deleteBookBy/{bookId}"})
    public ResponseEntity<Object> deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping(value = {"/getBookLocation1/{bookId}"})
    public  @ResponseBody Location getLocationOfBookBy(@PathVariable Long bookId) {
        return bookService.getLocationOfBookBy(bookId);
    }

}
