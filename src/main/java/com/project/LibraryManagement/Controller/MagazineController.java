package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MagazineController {

    @Autowired
    private MagazineService magazineService;

    @GetMapping(value = {"/getMagazines1"})
    public @ResponseBody List<Magazine> getMagazineList() {
        return magazineService.getAllMagazine();
    }

    @GetMapping(value = {"/getKeywordMagazine1/{id}"})
    public @ResponseBody List<Magazine> getMagazinesById(@PathVariable String id) {
        return magazineService.getSpecificMagazine(id);
    }

    @DeleteMapping(value = {"/librarian/deleteMagazineBy/{MagazineId}"})
    public ResponseEntity<Object> deleteMagazine(@PathVariable Long MagazineId) {
        return magazineService.deleteMagazine(MagazineId);
    }

    @PostMapping(value = {"/librarian/addMagazine"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addMagazine(@RequestBody Magazine magazine) {
        return magazineService.createMagazine(magazine);
    }
    @PutMapping(value = {"/librarian/updateMagazine"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMagazine(@RequestBody Magazine magazine) {
        return magazineService.updateMagazine(magazine);
    }

    @GetMapping(value = {"/getAllEditorsBy1/{magazineId}"})
    public @ResponseBody List<Editor> getEditorByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getAllEditorsByMagazineId(id);
    }

    @GetMapping(value = {"/getMagazineLocation1/{magazineId}"})
    public @ResponseBody Location getLocationOfMagazineBy(@PathVariable Long magazineId) {
        return magazineService.getLocationOfMagazineBy(magazineId);
    }

    @GetMapping(value = {"/getPublisherByMagazineId1/{magazineId}"})
    public @ResponseBody Publisher getPublisherByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getPublisherByMagazineId(id);
    }

    @GetMapping(value = {"/getAllContributorsByMagazineId1/{magazineId}"})
    public @ResponseBody List<Contributor> getAllContributorsByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getAllContributorsByMagazineId(id);
    }

    @GetMapping(value = {"/getAllIssuesByMagazineId1/{magazineId}"})
    public @ResponseBody List<Issues> getAllIssuesByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getAllIssuesByMagazineId(id);
    }

}
