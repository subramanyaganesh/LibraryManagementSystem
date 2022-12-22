package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @GetMapping(value = {"/getThesis1"})
    public @ResponseBody List<Thesis> getThesisList() {
        return thesisService.getAllThesis();
    }

    @GetMapping(value = {"/getKeywordThesis1/{id}"})
    public @ResponseBody List<Thesis> getThesisById(@PathVariable String id) {
        return thesisService.getSpecificThesis(id);
    }

    @PostMapping(value = {"/librarian/addThesis"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addThesis(@RequestBody Thesis thesis) {
        return thesisService.createThesis(thesis);
    }

    @PutMapping(value = {"/librarian/updateThesis"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateThesis(@RequestBody Thesis thesis) {
        return thesisService.updateThesis(thesis);
    }


    @DeleteMapping(value = {"/librarian/deleteThesisBy/{ThesisId}"})
    public ResponseEntity<Object> deleteThesis(@PathVariable Long ThesisId) {
        return thesisService.deleteThesis(ThesisId);
    }

    @GetMapping(value = {"/getThesisLocation1/{thesisId}"})
    public @ResponseBody Location getLocationOfThesisBy(@PathVariable Long thesisId) {
        return thesisService.getLocationOfThesisBy(thesisId);
    }

}
