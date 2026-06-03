package com.DashboardService.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.DashboardService.dto.ExecutiveReportResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generarReporteEjecutivo(
            ExecutiveReportResponse reporte) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font titulo = new Font(
                    Font.HELVETICA,
                    18,
                    Font.BOLD);

            Font contenido = new Font(
                    Font.HELVETICA,
                    12);

            document.add(
                    new Paragraph(
                            "GRUPO CORDILLERA",
                            titulo));

            document.add(new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "REPORTE EJECUTIVO",
                            titulo));

            document.add(new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Fecha: " + reporte.getFecha(),
                            contenido));

            document.add(new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Ventas Totales: $"
                                    + reporte.getVentasTotales(),
                            contenido));

            document.add(
                    new Paragraph(
                            "Cantidad Ventas: "
                                    + reporte.getCantidadVentas(),
                            contenido));

            document.add(
                    new Paragraph(
                            "Producto Mas Vendido: "
                                    + reporte.getProductoMasVendido(),
                            contenido));

            document.add(
                    new Paragraph(
                            "Mejor Sucursal: "
                                    + reporte.getMejorSucursal(),
                            contenido));

            document.add(new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "IMPORTANTE: Este reporte es confidencial y solo para uso interno.",
                            contenido));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al generar PDF",
                    e);
        }

        return new ByteArrayInputStream(
                out.toByteArray());
    }
}