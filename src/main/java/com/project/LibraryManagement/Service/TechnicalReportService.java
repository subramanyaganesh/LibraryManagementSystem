package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.TechnicalReportRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Object> createTechnicalReport(TechnicalReport technicalReport) {
        try {
            if (publisherService.getPublishersByEmail(technicalReport.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(technicalReport.getPublisher());
            technicalReport.setPublisher(publisherService.getPublishersByEmail(technicalReport.getPublisher().getEmailId()).get());

            if (writerService.getWriterByEmail(technicalReport.getWriter().getEmailId()).isEmpty())
                writerService.createWriter(technicalReport.getWriter());
            technicalReport.setWriter(writerService.getWriterByEmail(technicalReport.getWriter().getEmailId()).get());

            TechnicalReport result = technicalReportRepository.save(technicalReport);
            return ResponseHandler.generateResponse("Successfully added TechnicalReport!", HttpStatus.CREATED, result, TechnicalReport.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, TechnicalReport.class.getSimpleName());
        }
    }

    public ResponseEntity<Object> updateTechnicalReport(TechnicalReport technicalReport) {
        try {
            TechnicalReport specificBook = technicalReportRepository.findById(technicalReport.getDocument_id()).orElseThrow(()->new Exception("TechnicalReport Not Found"));
            if (publisherService.getPublishersByEmail(technicalReport.getPublisher().getEmailId()).isEmpty())
                publisherService.createPublisher(technicalReport.getPublisher());
            specificBook.setPublisher(publisherService.getPublishersByEmail(technicalReport.getPublisher().getEmailId()).get());

            if (writerService.getWriterByEmail(technicalReport.getWriter().getEmailId()).isEmpty())
                writerService.createWriter(technicalReport.getWriter());
            specificBook.setWriter(writerService.getWriterByEmail(technicalReport.getWriter().getEmailId()).get());

            specificBook.setLocation(technicalReport.getLocation());
            specificBook.setCopyNumber(technicalReport.getCopyNumber());
            specificBook.setDocument_id(technicalReport.getDocument_id());
            specificBook.setCategory(technicalReport.getCategory());
            specificBook.setYear(technicalReport.getYear());
            specificBook.setTitle(technicalReport.getTitle());
            TechnicalReport result = technicalReportRepository.save(specificBook);
            return ResponseHandler.generateResponse("Successfully added TechnicalReport!", HttpStatus.CREATED, result, TechnicalReport.class.getSimpleName());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, TechnicalReport.class.getSimpleName());
        }
    }


    public ResponseEntity<String> deleteTechnicalReport(Long id) {
        try {
            var book = technicalReportRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("TechnicalReport not found with ID %d", id)));

            technicalReportRepository.deleteById(book.getDocument_id());
            return ResponseHandler.generateResponse("Successfully Deleted TechnicalReport!", HttpStatus.OK, "Success!!", "TechnicalReport");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, TechnicalReport.class.getSimpleName());
        }
    }


    public Location getLocationOfTechnicalReportBy(Long id) {
        TechnicalReport technicalReport = technicalReportRepository.findById(id).orElse(null);
        if (technicalReport != null) {
            return technicalReport.getLocation();
        }
        throw new IllegalStateException("The Writer does not exist");
    }


}
