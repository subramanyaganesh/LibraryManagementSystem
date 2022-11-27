package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    public PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublishersById(Long id) {
        return publisherRepository.findById(id);
    }
    public Optional<Publisher> getPublishersByEmail(String id) {
        return publisherRepository.findByemailId(id);
    }

    public ResponseEntity<String> createPublisher(Publisher magazine) {
        publisherRepository.save(magazine);
        return new ResponseEntity<>("Successfully added Publisher", HttpStatus.OK);
    }

    public ResponseEntity<String> deletePublisher(Long id) {
        var publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Publisher not found with ID %d", id)));

        publisherRepository.deleteById(publisher.getPublisherId());
        return new ResponseEntity<>("Successfully Deleted Publisher", HttpStatus.OK);
    }

    public List<Book> getAllBooksByPublisherId(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            return new ArrayList<>(publisher.getBooks());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Journal> getAllJournalsByPublisherId(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            return new ArrayList<>(publisher.getJournalSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Thesis> getAllThesisByPublisherId(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            return new ArrayList<>(publisher.getThesisSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<Magazine> getAllMagazinesByPublisherId(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            return new ArrayList<>(publisher.getMagazineSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<TechnicalReport> getAllTechnicalReportByPublisherId(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            return new ArrayList<>(publisher.getTechnicalReportSet());
        }
        throw new IllegalStateException("The Writer does not exist");
    }
}
