package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class LibrarianController {


    @Autowired
    private LibrarianService librarianService;

    @GetMapping(value = "/")
    public String homePage() {
        return "index";
    }

    @GetMapping(value = {"/librarian/getLibrarian1"})
    public @ResponseBody  List<Librarian> getLibrarianList() {
        return librarianService.getAllLibrarians();
    }
    @GetMapping(value = {"/librarian/getAllLibrarianEmail"})
    public @ResponseBody  List<String> getLibrarianEmailList() {
        return librarianService.getAllLibrarianEmail();
    }


    @PostMapping(value = {"/librarian/postLibrarian"})
    public ResponseEntity<Object> postLibrarian(@RequestBody Librarian librarians) {
        return librarianService.registerNewLibrarian(librarians);
    }
    @PutMapping(value = {"/librarian/updateLibrarian"})
    public ResponseEntity<Object> putLibrarian(@RequestBody Librarian librarians) {
        return librarianService.updateLibrarian(librarians);
    }

    @DeleteMapping(value = "/librarian/deleteLibrarian/{librarian_id}")
    public ResponseEntity<Object>  deleteLibrarian(@PathVariable("librarian_id") Long id) {
        return librarianService.deleteLibrarian(id);
    }

}
