package com.DashboardService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DashboardService.dto.ExecutiveReportResponse;
import com.DashboardService.service.DashboardService;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.DashboardService.service.PdfGeneratorService;
import com.DashboardService.dto.ProductReportResponse;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final DashboardService dashboardService;

    private final PdfGeneratorService pdfGeneratorService;

    public ReportController( DashboardService dashboardService, PdfGeneratorService pdfGeneratorService) {

        this.dashboardService = dashboardService;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/executive")
    public ExecutiveReportResponse getExecutiveReport() {
        return dashboardService.getExecutiveReport();
    }

    @GetMapping("/executive/pdf")
    public ResponseEntity<InputStreamResource> descargarReporteEjecutivo() {

        ExecutiveReportResponse reporte =
                dashboardService.getExecutiveReport();

        ByteArrayInputStream pdf =
                pdfGeneratorService
                        .generarReporteEjecutivo(reporte);

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "attachment; filename=reporte-ejecutivo.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("/products")
    public ProductReportResponse getProductsReport() {

        return dashboardService.getProductsReport();
    }

    @GetMapping("/products/pdf")
    public ResponseEntity<InputStreamResource> descargarReporteProductos() {

        ProductReportResponse reporte =
                dashboardService.getProductsReport();

        ByteArrayInputStream pdf =
                pdfGeneratorService
                        .generarReporteProductos(reporte);

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "attachment; filename=reporte-productos.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}