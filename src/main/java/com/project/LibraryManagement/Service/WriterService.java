package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WriterService {
    @Autowired
    public WriterRepository writerRepository;

    public List<Writer> getAllWriters() {
        return writerRepository.findAll();
    }

    public Optional<Writer> getWriterByEmail(String id) {
        return writerRepository.findByemailId(id);
    }

    public ResponseEntity<String> createWriter(Writer writer) {
        writerRepository.save(writer);
        return new ResponseEntity<>("Successfully added Writer", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteWriter(Long id) {
        var writer = writerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Writer not found with ID %d", id)));

        writerRepository.deleteById(writer.getWriterId());
        return new ResponseEntity<>("Successfully Deleted Writer", HttpStatus.OK);
    }

    public List<Thesis> getAllThesisByWriterId(String id) {
        Writer writer;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            writer = writerRepository.findById(i).orElse(null);
        } else {
            writer = writerRepository.findByfirstName(id).orElse(null);
        }
        if (writer != null) {
            return writer.getThesisSet().stream().toList();
        }
        throw new IllegalStateException("The Writer does not exist");
    }

    public List<TechnicalReport> getAllTechnicalReportByWriterId(String id) {
        Writer writer;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            writer = writerRepository.findById(i).orElse(null);
        } else {
            writer = writerRepository.findByfirstName(id).orElse(null);
        }

        if (writer != null) {
            return writer.getTechnicalReportSet().stream().toList();
        }
        throw new IllegalStateException("The Writer does not exist");
    }
}
