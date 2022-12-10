package com.project.LibraryManagement.Controller;


import com.project.LibraryManagement.Model.Issues;
import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IssueController {

    @Autowired
    private IssuesService IssueService;

    @GetMapping(value = {"/getIssues"})
    public List<Issues> getIssueList() {
        return IssueService.getAllIssues();
    }

    @PostMapping(value = {"/librarian/addIssue", "/librarian/updateIssue"})
    public ResponseEntity<String> addIssue(@RequestBody Issues Issue) {
        return IssueService.createIssues(Issue);
    }

    @DeleteMapping(value = {"/librarian/deleteIssueBy/{IssueId}"})
    public ResponseEntity<String> deleteIssue(@PathVariable Long IssueId) {
        return IssueService.deleteIssues(IssueId);
    }

    @GetMapping(value = {"/getAllMagazinesByIssueId/{IssueId}"})
    public List<Magazine> getAllMagazinesByIssues(@PathVariable Long IssueId) {
        return IssueService.getAllMagazinesByIssuesId(IssueId);
    }
    @GetMapping(value = {"/getAllJournalsByIssuesId/{IssueId}"})
    public List<Journal> getAllJournalsByIssuesId(@PathVariable Long IssueId) {
        return IssueService.getAllJournalsByIssuesId(IssueId);
    }

}
