package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping(value = {"/getEditors1"})
    public @ResponseBody List<Editor> getEditorList() {
        return editorService.getAllEditors();
    }

    @GetMapping(value = {"/getMagazinesByEditorId1/{EditorId}"})
    public @ResponseBody List<Magazine> getMagazinesByEditor(@PathVariable String EditorId) {
        return editorService.getAllMagazinesByEditorId(EditorId);
    }

    @GetMapping(value = {"/getJournalsByEditorId1/{EditorId}"})
    public @ResponseBody List<Journal> getJournalsByEditorId(@PathVariable String EditorId) {
        return editorService.getAllJournalsByEditorId(EditorId);
    }
}
