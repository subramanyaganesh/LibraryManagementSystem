package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.TechnicalReport;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import com.project.LibraryManagement.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WriterController {

    @Autowired
    private WriterService writerService;

    @GetMapping(value = {"/getWriters"})
    public List<Writer> getMembersList() {
        return writerService.getAllWriters();
    }

    @GetMapping(value = {"/getAllThesisByWriter/{writerId}"})
    public List<Thesis> getThesisByWriterId(@PathVariable("writerId") String id) {
        return writerService.getAllThesisByWriterId(id);
    }

    @GetMapping(value = {"/getAllTechnicalReportByWriter/{writerId}"})
    public List<TechnicalReport> getAllTechnicalReportByWriterId(@PathVariable("writerId") String id) {
        return writerService.getAllTechnicalReportByWriterId(id);
    }

}
