package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Model.TechnicalReport;
import com.project.LibraryManagement.Service.TechnicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TechnicalReportController {

    @Autowired
    private TechnicalReportService technicalReportService;

    @GetMapping(value = {"/getTechnicalReport1"})
    public @ResponseBody List<TechnicalReport> getTechnicalReportList() {
        return technicalReportService.getAllTechnicalReport();
    }

    @GetMapping(value = {"/getKeywordTechnicalReport1/{id}"})
    public @ResponseBody List<TechnicalReport> getTechnicalReportById(@PathVariable String id) {
        return technicalReportService.getSpecificTechnicalReport(id);
    }

    @PostMapping(value = {"/librarian/addTechnicalReport"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addTechnicalReport(@RequestBody TechnicalReport technicalReport) {
        return technicalReportService.createTechnicalReport(technicalReport);
    }
    @PutMapping(value = { "/librarian/updateTechnicalReport"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTechnicalReport(@RequestBody TechnicalReport technicalReport) {
        return technicalReportService.updateTechnicalReport(technicalReport);
    }

    @DeleteMapping(value = {"/librarian/deleteTechnicalReportBy/{TechnicalReportId}"})
    public ResponseEntity<String> deleteTechnicalReport(@PathVariable Long TechnicalReportId) {
        return technicalReportService.deleteTechnicalReport(TechnicalReportId);
    }

    @GetMapping(value = {"/getTechnicalReportLocation1/{technicalReportId}"})
    public @ResponseBody Location getLocationOfTechnicalReportBy(@PathVariable Long technicalReportId) {
        return technicalReportService.getLocationOfTechnicalReportBy(technicalReportId);
    }

}
