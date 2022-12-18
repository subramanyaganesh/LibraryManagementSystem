package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EditorService {
    @Autowired
    public EditorRepository editorRepository;

    public List<Editor> getAllEditors() {
        return editorRepository.findAll();
    }

    public Optional<Editor> getAuthorByEmail(String email) {
        return editorRepository.findByemailId(email);
    }
    public List<Magazine> getAllMagazinesByEditorId(String id) {
        Editor editor;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            editor = editorRepository.findById(i).orElse(null);
        } else {
            editor = editorRepository.findByfirstName(id).orElse(null);
        }
        if (editor != null) {
            Set<Issues> issues = editor.getIssues();
            List<Magazine> magazines = new ArrayList<>();
            issues.stream().map(Issues::getMagazines).forEach(magazines::addAll);
            return magazines;
        }
        throw new IllegalStateException("The Editor does not exist");
    }

    public List<Journal> getAllJournalsByEditorId(String id) {
        Editor editor;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            editor = editorRepository.findById(i).orElse(null);
        } else {
            editor = editorRepository.findByfirstName(id).orElse(null);
        }
        if (editor != null) {
            Set<Issues> issues = editor.getIssues();
            List<Journal> journals = new ArrayList<>();
            issues.stream().map(Issues::getJournals).forEach(journals::addAll);
            return journals;
        }
        throw new IllegalStateException("The Editor does not exist");
    }

    public ResponseEntity<String> createEditor(Editor editor) {
        editorRepository.save(editor);
        return new ResponseEntity<>("Successfully added editor", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEditor(Long id) {
        var Editor = editorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Editor not found with ID %d", id)));

        editorRepository.deleteById(Editor.getEditorId());
        return new ResponseEntity<>("Successfully Deleted Editor", HttpStatus.OK);
    }
}
