package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditorService {
    @Autowired
    public EditorRepository editorRepository;

    public List<Editor> getAllEditors() {
        return editorRepository.findAll();
    }

    public List<Magazine> getAllMagazinesByEditorId(Long id){
        Editor editor= editorRepository.findById(id).orElse(null);
        if (editor!=null){
            return new ArrayList<>(editor.getIssues().getMagazines());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Journal> getAllJournalsByEditorId(Long id){
        Editor editor= editorRepository.findById(id).orElse(null);
        if (editor!=null){
            return new ArrayList<>(editor.getIssues().getJournals());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

}
