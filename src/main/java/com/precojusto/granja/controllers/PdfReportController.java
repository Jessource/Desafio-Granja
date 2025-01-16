package com.precojusto.granja.controllers;

import com.precojusto.granja.services.PdfReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports/pdf")
public class PdfReportController {

    @Autowired
    private PdfReportService pdfReportService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadReport() throws Exception {
        return pdfReportService.getReportAsResponseEntity();
    }
}
