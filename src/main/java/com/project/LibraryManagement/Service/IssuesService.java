package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.IssuesRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IssuesService {
    @Autowired
    public IssuesRepository issuesRepository;
    @Autowired
    public EditorService editorService;

    public List<Issues> getAllIssues() {
        return issuesRepository.findAll();
    }

    public Optional<Issues> getIssueById(Long id) {
        return issuesRepository.findbyid(id);
    }

    public List<Magazine> getAllMagazinesByIssuesId(Long id) {
        Issues Issues = issuesRepository.findById(id).orElse(null);
        if (Issues != null) {
            return new ArrayList<>(Issues.getMagazines());
        }
        throw new IllegalStateException("The Issues does not exist");
    }

    public List<Journal> getAllJournalsByIssuesId(Long id) {
        Issues Issues = issuesRepository.findById(id).orElse(null);
        if (Issues != null) {
            return new ArrayList<>(Issues.getJournals());
        }
        throw new IllegalStateException("The Issues does not exist");
    }


    public ResponseEntity<Object> createIssues(Issues issues) {
        try {
            Set<Editor> editorSet = new HashSet<>();
            issues.getEditorSet().forEach(editor -> {
                if (editorService.getAuthorByEmail(editor.getEmailId()).isEmpty())
                    editorSet.add(editor);
            });
            issues.setEditorSet(editorSet);
            Issues result = issuesRepository.save(issues);
            return ResponseHandler.generateResponse("Successfully added Issues!", HttpStatus.CREATED, result, Issues.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Issues.class.getSimpleName());
        }

    }

    public ResponseEntity<Object> updateIssues(Issues issues) {
        try {
            Set<Editor> editorSet = new HashSet<>();
            Issues specificBook = issuesRepository.findbyid(issues.getIssueId()).orElseThrow(Exception::new);

            issues.getEditorSet().forEach(editor -> {
                if (editorService.getAuthorByEmail(editor.getEmailId()).isEmpty())
                    editorSet.add(editor);
            });
            specificBook.setEditorSet(editorSet);
            specificBook.setIssueId(issues.getIssueId());
            specificBook.setPublishDate(issues.getPublishDate());
            specificBook.setJournals(issues.getJournals());
            specificBook.setMagazines(issues.getMagazines());
            Issues result = issuesRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully added Issues!", HttpStatus.CREATED, result, Issues.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Issues.class.getSimpleName());
        }

    }


    public ResponseEntity<Object> deleteIssues(Long id) {
        try {
            var Issues = issuesRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("Issues not found with ID %d", id)));

            issuesRepository.deleteById(Issues.getIssueId());
            return ResponseHandler.generateResponse("Successfully Deleted Issues!", HttpStatus.OK, "Success!!", "Issues");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Issues.class.getSimpleName());
        }
    }
}
