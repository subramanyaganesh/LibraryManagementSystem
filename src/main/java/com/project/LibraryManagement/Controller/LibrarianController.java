package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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


    @PostMapping(value = {"/librarian/postLibrarian"})
    public ResponseEntity<String> postLibrarian(@RequestBody Librarian librarians) {
        return librarianService.registerNewLibrarian(librarians);
    }
    @PutMapping(value = {"/librarian/updateLibrarian"})
    public ResponseEntity<String> putLibrarian(@RequestBody Librarian librarians) {
        return librarianService.updateLibrarian(librarians);
    }

    @DeleteMapping(value = "/librarian/deleteLibrarian/{librarian_id}")
    public void deleteLibrarian(@PathVariable("librarian_id") Long id) {
        librarianService.deleteLibrarian(id);
    }

}
