package com.project.LibraryManagement.Controller;


import com.project.LibraryManagement.Model.Issues;
import com.project.LibraryManagement.Model.Journal;
import com.project.LibraryManagement.Model.Magazine;
import com.project.LibraryManagement.Service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IssueController {

    @Autowired
    private IssuesService IssueService;

    @GetMapping(value = {"/getIssues1"})
    public @ResponseBody List<Issues> getIssueList() {
        return IssueService.getAllIssues();
    }

    @PostMapping(value = {"/librarian/addIssue"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addIssue(@RequestBody Issues Issue) {
        return IssueService.createIssues(Issue);
    }

    @PutMapping(value = {"/librarian/updateIssue"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateIssue(@RequestBody Issues Issue) {
        return IssueService.updateIssues(Issue);
    }

    @DeleteMapping(value = {"/librarian/deleteIssueBy/{IssueId}"})
    public ResponseEntity<Object> deleteIssue(@PathVariable Long IssueId) {
        return IssueService.deleteIssues(IssueId);
    }

    @GetMapping(value = {"/getAllMagazinesByIssueId1/{IssueId}"})
    public @ResponseBody List<Magazine> getAllMagazinesByIssues(@PathVariable Long IssueId) {
        return IssueService.getAllMagazinesByIssuesId(IssueId);
    }

    @GetMapping(value = {"/getAllJournalsByIssuesId1/{IssueId}"})
    public @ResponseBody List<Journal> getAllJournalsByIssuesId(@PathVariable Long IssueId) {
        return IssueService.getAllJournalsByIssuesId(IssueId);
    }

}
