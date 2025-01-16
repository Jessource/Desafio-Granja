package com.precojusto.granja.controllers;

import com.precojusto.granja.model.Duck;
import com.precojusto.granja.repositories.DuckRepository;
import com.precojusto.granja.services.ExcelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports/excel")
public class ExcelReportController {

    private final ExcelReportService excelReportService;
    private final DuckRepository duckRepository; // Repositório para buscar dados do banco

    @Autowired
    public ExcelReportController(ExcelReportService excelReportService, DuckRepository duckRepository) {
        this.excelReportService = excelReportService;
        this.duckRepository = duckRepository;
    }

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> generateExcelReport() {
        try {
            List<Duck> ducks = duckRepository.findAll();
            ByteArrayInputStream excelFile = excelReportService.generateExcelReport(ducks);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=gerenciamento_de_patos.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(excelFile));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
