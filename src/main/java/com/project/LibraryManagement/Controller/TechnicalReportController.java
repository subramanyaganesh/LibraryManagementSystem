package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Location;
import com.project.LibraryManagement.Model.TechnicalReport;
import com.project.LibraryManagement.Service.TechnicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TechnicalReportController {

    @Autowired
    private TechnicalReportService technicalReportService;

    @GetMapping(value = {"/getTechnicalReport"})
    public List<TechnicalReport> getTechnicalReportList() {
        return technicalReportService.getAllTechnicalReport();
    }

    @PostMapping(value = {"/addTechnicalReport","/updateTechnicalReport"})
    public ResponseEntity<String> addTechnicalReport(@RequestBody TechnicalReport thesis) {
        return technicalReportService.createTechnicalReport(thesis);
    }
    @DeleteMapping(value = {"/deleteTechnicalReportBy/{TechnicalReportId}"})
    public ResponseEntity<String> deleteTechnicalReport(Long TechnicalReportId) {
        return technicalReportService.deleteTechnicalReport(TechnicalReportId);
    }
    @GetMapping(value = {"/getTechnicalReportLocation/{technicalReportId}"})
    public Location getLocationOfTechnicalReportBy(@PathVariable Long technicalReportId) {
        return technicalReportService.getLocationOfTechnicalReportBy(technicalReportId);
    }

}
