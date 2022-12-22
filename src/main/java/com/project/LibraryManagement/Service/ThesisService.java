package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Repository.ThesisRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public ResponseEntity<Object> createThesis(Thesis thesis) {
        try {
            if (publisherService.getPublishersByEmail(thesis.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(thesis.getPublisher());
            thesis.setPublisher(publisherService.getPublishersByEmail(thesis.getPublisher().getEmailId()).get());

            if (writerService.getWriterByEmail(thesis.getWriter().getEmailId()).isEmpty())
                writerService.createWriter(thesis.getWriter());
            thesis.setWriter(writerService.getWriterByEmail(thesis.getWriter().getEmailId()).get());

            Thesis result = thesisRepository.save(thesis);
            return ResponseHandler.generateResponse("Successfully added Thesis!", HttpStatus.CREATED, result, Thesis.class.getSimpleName());
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, "Thesis");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Thesis");
        }
    }



    public ResponseEntity<Object> updateThesis(Thesis thesis) {
        try {
            Thesis specificBook = thesisRepository.findById(thesis.getDocument_id()).orElseThrow(()->new Exception("Thesis Not Found"));
            if (publisherService.getPublishersByEmail(thesis.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(thesis.getPublisher());
            specificBook.setPublisher(publisherService.getPublishersByEmail(thesis.getPublisher().getEmailId()).get());

            if (writerService.getWriterByEmail(thesis.getWriter().getEmailId()).isEmpty())
                writerService.createWriter(thesis.getWriter());
            specificBook.setWriter(writerService.getWriterByEmail(thesis.getWriter().getEmailId()).get());
            specificBook.setCategory(thesis.getCategory());
            specificBook.setTitle(thesis.getTitle());
            specificBook.setYear(thesis.getYear());
            specificBook.setLocation(thesis.getLocation());
            specificBook.setCopyNumber(thesis.getCopyNumber());

            Thesis result = thesisRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully updated Thesis!", HttpStatus.CREATED, result, Thesis.class.getSimpleName());
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, "Thesis");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Thesis");
        }
    }


    public ResponseEntity<Object> deleteThesis(Long id) {
        try {
            var book = thesisRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("Thesis not found with ID %d", id)));

            thesisRepository.deleteById(book.getDocument_id());
            return ResponseHandler.generateResponse("Successfully Deleted Thesis!", HttpStatus.OK, "Success!!", "Thesis");
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getMostSpecificCause(), HttpStatus.BAD_REQUEST, null, "Thesis");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is :: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Thesis");
        }
    }



    public Location getLocationOfThesisBy(Long id) {
        Thesis thesis = thesisRepository.findById(id).orElse(null);
        if (thesis != null) {
            return thesis.getLocation();
        }
        throw new IllegalStateException("The Thesis does not exist");
    }


}
