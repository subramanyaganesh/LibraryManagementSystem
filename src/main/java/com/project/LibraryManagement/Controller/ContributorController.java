package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Contributor;
import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class ContributorController {

    @Autowired
    private ContributorService contributorService;

    @GetMapping(value = {"/getContributors1"})
    public @ResponseBody List<Contributor> getContributorList() {
        return contributorService.getAllContributors();
    }

    @GetMapping(value = {"/getMagazinesByContributor1/{contributorId}"})
    public @ResponseBody List<Magazine> getMagazines(@PathVariable String contributorId) {
        return contributorService.getAllMagazinesByContributorId(contributorId);
    }

}
