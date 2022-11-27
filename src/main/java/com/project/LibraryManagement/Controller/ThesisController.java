package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @GetMapping(value = {"/getThesis"})
    public List<Thesis> getThesisList() {
        return thesisService.getAllThesis();
    }

    @PostMapping(value = {"/addThesis", "/updateThesis"})
    public ResponseEntity<String> addThesis(@RequestBody Thesis thesis) {
        return thesisService.createThesis(thesis);
    }

    @DeleteMapping(value = {"/deleteThesisBy/{ThesisId}"})
    public ResponseEntity<String> deleteThesis(Long ThesisId) {
        return thesisService.deleteThesis(ThesisId);
    }

    @GetMapping(value = {"/getThesisLocation/{thesisId}"})
    public Location getLocationOfThesisBy(@PathVariable Long thesisId) {
        return thesisService.getLocationOfThesisBy(thesisId);
    }

}
