package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MagazineController {

    @Autowired
    private MagazineService magazineService;

    @GetMapping(value = {"/getMagazines"})
    public List<Magazine> getMagazineList() {
        return magazineService.getAllMagazine();
    }

    @DeleteMapping(value = {"/deleteMagazineBy/{MagazineId}"})
    public ResponseEntity<String> deleteMagazine(Long MagazineId) {
        return magazineService.deleteMagazine(MagazineId);
    }
    @PostMapping(value = {"/addMagazine","/updateMagazine"})
    public ResponseEntity<String> addMagazine(@RequestBody Magazine magazine) {
        return magazineService.createMagazine(magazine);
    }

    @GetMapping(value = {"/getAllEditorsBy/{magazineId}"})
    public List<Editor> getEditorByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getAllEditorsByMagazineId(id);
    }
    @GetMapping(value = {"/getMagazineLocation/{magazineId}"})
    public Location getLocationOfMagazineBy(@PathVariable Long magazineId) {
        return magazineService.getLocationOfMagazineBy(magazineId);
    }
    @GetMapping(value = {"/getPublisherByMagazineId/{magazineId}"})
    public Publisher getPublisherByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getPublisherByMagazineId(id);
    }

    @GetMapping(value = {"/getAllContributorsByMagazineId/{magazineId}"})
    public List<Contributor> getAllContributorsByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getAllContributorsByMagazineId(id);
    }
    @GetMapping(value = {"/getAllIssuesByMagazineId/{magazineId}"})
    public List<Issues> getAllIssuesByMagazineId(@PathVariable("magazineId") Long id) {
        return magazineService.getAllIssuesByMagazineId(id);
    }

}
