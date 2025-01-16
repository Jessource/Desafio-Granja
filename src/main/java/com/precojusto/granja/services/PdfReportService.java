package com.precojusto.granja.services;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfReportService {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private DataSource dataSource;
    public byte[] generateReport() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Resource resource = resourceLoader.getResource("classpath:reports/Blank_A4.jasper");
            if (!resource.exists()) {
                throw new IllegalArgumentException("Arquivo .jasper não encontrado");
            }
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource.getInputStream(), parameters, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }

    public ResponseEntity<byte[]> getReportAsResponseEntity() throws Exception {
        byte[] reportBytes = generateReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(reportBytes);
    }
}