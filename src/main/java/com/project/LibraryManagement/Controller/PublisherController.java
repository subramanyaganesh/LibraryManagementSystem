package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping(value = {"/getPublishers1"})
    public @ResponseBody List<Publisher> getPublisherList() {
        return publisherService.getAllPublishers();
    }

    @GetMapping(value = {"/getAllBooksBy1/{publisherId}"})
    public @ResponseBody List<Book> getBooksByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllBooksByPublisherId(id);
    }

    @GetMapping(value = {"/getAllJournalsBy1/{publisherId}"})
    public @ResponseBody List<Journal> getJournalsByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllJournalsByPublisherId(id);
    }

    @GetMapping(value = {"/getAllMagazinesBy1/{publisherId}"})
    public @ResponseBody List<Magazine> getMagazinesByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllMagazinesByPublisherId(id);
    }

    @GetMapping(value = {"/getAllThesisBy1/{publisherId}"})
    public @ResponseBody List<Thesis> getThesisByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllThesisByPublisherId(id);
    }

    @GetMapping(value = {"/getAllTechnicalReportsBy1/{publisherId}"})
    public @ResponseBody List<TechnicalReport> getTechnicalReportsByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllTechnicalReportByPublisherId(id);
    }

}
