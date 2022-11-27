package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Contributor;
import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContributorController {

    @Autowired
    private ContributorService contributorService;

    @GetMapping(value = {"/getContributors"})
    public List<Contributor> getContributorList() {
        return contributorService.getAllContributors();
    }

    @GetMapping(value = {"/getMagazinesByContributorId/{contributorId}"})
    public List<Magazine> getMagazines(@PathVariable Long contributorId) {
        return contributorService.getAllMagazinesByContributorId(contributorId);
    }

}
