package com.DashboardService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.DashboardService.dto.ExecutiveReportResponse;
import com.DashboardService.dto.FullReportResponse;
import com.DashboardService.dto.ProductReportResponse;
import com.DashboardService.dto.SalesReportResponse;
import com.DashboardService.service.DashboardService;
import com.DashboardService.service.PdfGeneratorService;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock
    private DashboardService dashboardService;

    @Mock
    private PdfGeneratorService pdfGeneratorService;

    @InjectMocks
    private ReportController reportController;

    @Test
    void getExecutiveReport_retornaReporte() {

        ExecutiveReportResponse reporte =
                new ExecutiveReportResponse();

        when(dashboardService.getExecutiveReport())
                .thenReturn(reporte);

        var response =
                reportController.getExecutiveReport();

        assertEquals(
                reporte,
                response);

        verify(dashboardService)
                .getExecutiveReport();
    }


    @Test
    void descargarReporteEjecutivo_retornaPdf() {

        ExecutiveReportResponse reporte =
                new ExecutiveReportResponse();

        ByteArrayInputStream pdf =
                new ByteArrayInputStream(
                        "pdf".getBytes());

        when(dashboardService.getExecutiveReport())
                .thenReturn(reporte);

        when(pdfGeneratorService
                .generarReporteEjecutivo(reporte))
                .thenReturn(pdf);

        var response =
                reportController
                        .descargarReporteEjecutivo();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode());

        assertEquals(
                MediaType.APPLICATION_PDF,
                response.getHeaders()
                        .getContentType());

        verify(dashboardService)
                .getExecutiveReport();

        verify(pdfGeneratorService)
                .generarReporteEjecutivo(reporte);
    }


    @Test
    void getProductsReport_retornaReporte() {

        ProductReportResponse reporte =
                new ProductReportResponse();

        when(dashboardService.getProductsReport())
                .thenReturn(reporte);

        var response =
                reportController.getProductsReport();

        assertEquals(
                reporte,
                response);

        verify(dashboardService)
                .getProductsReport();
    }


    @Test
    void descargarReporteProductos_retornaPdf() {

        ProductReportResponse reporte =
                new ProductReportResponse();

        ByteArrayInputStream pdf =
                new ByteArrayInputStream(
                        new byte[]{1});

        when(dashboardService.getProductsReport())
                .thenReturn(reporte);

        when(pdfGeneratorService
                .generarReporteProductos(reporte))
                .thenReturn(pdf);

        var response =
                reportController
                        .descargarReporteProductos();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode());

        verify(pdfGeneratorService)
                .generarReporteProductos(reporte);
    }


    @Test
    void getSalesReport_retornaReporte() {

        SalesReportResponse reporte =
                new SalesReportResponse();

        when(dashboardService.getSalesReport())
                .thenReturn(reporte);

        var response =
                reportController.getSalesReport();

        assertEquals(
                reporte,
                response);

        verify(dashboardService)
                .getSalesReport();
    }


    @Test
    void descargarReporteVentas_retornaPdf() {

        SalesReportResponse reporte =
                new SalesReportResponse();

        ByteArrayInputStream pdf =
                new ByteArrayInputStream(
                        new byte[]{1});

        when(dashboardService.getSalesReport())
                .thenReturn(reporte);

        when(pdfGeneratorService
                .generarReporteVentas(reporte))
                .thenReturn(pdf);

        var response =
                reportController
                        .descargarReporteVentas();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode());

        verify(pdfGeneratorService)
                .generarReporteVentas(reporte);
    }


    @Test
    void getFullReport_retornaReporte() {

        FullReportResponse reporte =
                new FullReportResponse();

        when(dashboardService.getFullReport())
                .thenReturn(reporte);

        var response =
                reportController.getFullReport();

        assertEquals(
                reporte,
                response);

        verify(dashboardService)
                .getFullReport();
    }


    @Test
    void descargarReporteCompleto_retornaPdf() {

        FullReportResponse reporte =
                new FullReportResponse();

        ByteArrayInputStream pdf =
                new ByteArrayInputStream(
                        new byte[]{1});

        when(dashboardService.getFullReport())
                .thenReturn(reporte);

        when(pdfGeneratorService
                .generarReporteCompleto(reporte))
                .thenReturn(pdf);

        var response =
                reportController
                        .descargarReporteCompleto();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode());

        verify(pdfGeneratorService)
                .generarReporteCompleto(reporte);
    }

}