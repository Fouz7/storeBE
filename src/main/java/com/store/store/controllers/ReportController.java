package com.store.store.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.services.ReportService;

import net.sf.jasperreports.engine.JRException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RequestMapping("/store/report/")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("{format}")
    public ResponseEntity<?> exportReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportService.exportReport(format);
    }
}
