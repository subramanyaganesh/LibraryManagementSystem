package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.TechnicalReport;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisService {
    @Autowired
    public ThesisRepository thesisRepository;
    @Autowired
    public PublisherService publisherService;

    @Autowired
    public WriterService writerService;

    public List<Thesis> getSpecificThesis(String id) {
        List<Thesis> thesis;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            thesis = List.of(thesisRepository.findById(i).orElse(new Thesis()));
        } else {
            thesis = thesisRepository.findBytitleContainingIgnoreCase(id);
        }
        if (thesis != null) {
            return thesis;
        }
        throw new IllegalStateException("The Thesis does not exist");
    }
    public List<Thesis> getAllThesis() {
        return thesisRepository.findAll();
    }

    public ResponseEntity<String> createThesis(Thesis thesis) {
        if (publisherService.getPublishersByEmail(thesis.getPublisher().getEmailId()).isEmpty())
            publisherService.createPublisher(thesis.getPublisher());
        if (writerService.getWriterByEmail(thesis.getWriter().getEmailId()).isEmpty())
            writerService.createWriter(thesis.getWriter());
        thesisRepository.save(thesis);
        return new ResponseEntity<>("Successfully added Thesis", HttpStatus.OK);
    }


    public ResponseEntity<String> deleteThesis(Long id) {
        var book = thesisRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Thesis not found with ID %d", id)));

        thesisRepository.deleteById(book.getDocument_id());
        return new ResponseEntity<>("Successfully Deleted Thesis", HttpStatus.OK);
    }


    public Location getLocationOfThesisBy(Long id) {
        Thesis thesis = thesisRepository.findById(id).orElse(null);
        if (thesis != null) {
            return thesis.getLocation();
        }
        throw new IllegalStateException("The Thesis does not exist");
    }


}
