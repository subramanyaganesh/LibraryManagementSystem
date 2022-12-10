package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.TechnicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnicalReportService {
    @Autowired
    public TechnicalReportRepository technicalReportRepository;
    @Autowired
    public PublisherService publisherService;
    @Autowired
    public WriterService writerService;

    public List<TechnicalReport> getAllTechnicalReport() {
        return technicalReportRepository.findAll();
    }
    public List<TechnicalReport> getSpecificTechnicalReport(String id) {
        List<TechnicalReport> technicalReports;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            technicalReports = List.of(technicalReportRepository.findById(i).orElse(new TechnicalReport()));
        } else {
            technicalReports = technicalReportRepository.findBytitleContainingIgnoreCase(id);
        }
        if (technicalReports != null) {
            return technicalReports;
        }
        throw new IllegalStateException("The TechnicalReport does not exist");
    }
    public ResponseEntity<String> createTechnicalReport(TechnicalReport technicalReport) {
        if (publisherService.getPublishersByEmail(technicalReport.getPublisher().getEmailId()).isEmpty())
            publisherService.createPublisher(technicalReport.getPublisher());
        if (writerService.getWriterByEmail(technicalReport.getWriter().getEmailId()).isEmpty())
            writerService.createWriter(technicalReport.getWriter());
        technicalReportRepository.save(technicalReport);
        return new ResponseEntity<>("Successfully added Book", HttpStatus.OK);
    }


    public ResponseEntity<String> deleteTechnicalReport(Long id) {
        var book = technicalReportRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("TechnicalReport not found with ID %d", id)));

        technicalReportRepository.deleteById(book.getDocument_id());
        return new ResponseEntity<>("Successfully Deleted TechnicalReport", HttpStatus.OK);
    }


    public Location getLocationOfTechnicalReportBy(Long id) {
        TechnicalReport technicalReport = technicalReportRepository.findById(id).orElse(null);
        if (technicalReport != null) {
            return technicalReport.getLocation();
        }
        throw new IllegalStateException("The Writer does not exist");
    }


}
