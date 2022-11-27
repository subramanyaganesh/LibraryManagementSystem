package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping(value = {"/getEditors"})
    public List<Editor> getEditorList() {
        return editorService.getAllEditors();
    }

    @GetMapping(value = {"/getMagazinesByEditorId/{EditorId}"})
    public List<Magazine> getMagazinesByEditor(@PathVariable Long EditorId) {
        return editorService.getAllMagazinesByEditorId(EditorId);
    }

    @GetMapping(value = {"/getJournalsByEditorId/{EditorId}"})
    public List<Journal> getJournalsByEditorId(@PathVariable Long EditorId) {
        return editorService.getAllJournalsByEditorId(EditorId);
    }
}
