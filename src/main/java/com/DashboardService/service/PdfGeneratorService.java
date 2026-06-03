package com.DashboardService.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.DashboardService.dto.ExecutiveReportResponse;
import com.DashboardService.dto.ProductReportResponse;
import com.DashboardService.dto.ProductoResponse;
import com.DashboardService.dto.SalesReportResponse;
import com.DashboardService.dto.VentaResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

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

    public ByteArrayInputStream generarReporteProductos(
        ProductReportResponse reporte) {

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
                        10);

                Paragraph encabezado =
                        new Paragraph(
                                "GRUPO CORDILLERA",
                                titulo);

                encabezado.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(encabezado);

                document.add(new Paragraph(" "));

                Paragraph subtitulo =
                        new Paragraph(
                                "REPORTE DE PRODUCTOS",
                                titulo);

                subtitulo.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(subtitulo);

                document.add(new Paragraph(" "));

                document.add(
                        new Paragraph(
                                "Fecha: " + reporte.getFecha()));

                document.add(new Paragraph(" "));

                PdfPTable table = new PdfPTable(5);

                table.setWidthPercentage(100);

                table.addCell(new PdfPCell(new Paragraph("ID")));
                table.addCell(new PdfPCell(new Paragraph("PRODUCTO")));
                table.addCell(new PdfPCell(new Paragraph("CATEGORIA")));
                table.addCell(new PdfPCell(new Paragraph("PRECIO")));
                table.addCell(new PdfPCell(new Paragraph("STOCK")));

                NumberFormat formato =
                        NumberFormat.getCurrencyInstance(
                                new Locale("es", "CL"));

                for (ProductoResponse producto : reporte.getProductos()) {

                table.addCell(
                        String.valueOf(
                                producto.getIdProducto()));

                table.addCell(
                        producto.getNombre());

                table.addCell(
                        producto.getCategoria());

                table.addCell(
                        formato.format(
                                producto.getPrecio()));

                table.addCell(
                        String.valueOf(
                                producto.getStock()));
                }

                document.add(table);

                document.add(new Paragraph(" "));

                document.add(
                        new Paragraph(
                                "Total Productos: "
                                        + reporte.getTotalProductos(),
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

     public ByteArrayInputStream generarReporteVentas(
        SalesReportResponse reporte) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

                PdfWriter.getInstance(document, out);

                document.open();

                Font titulo = new Font(
                        Font.HELVETICA,
                        18,
                        Font.BOLD);

                Paragraph encabezado =
                        new Paragraph(
                                "GRUPO CORDILLERA",
                                titulo);

                encabezado.setAlignment(
                        Paragraph.ALIGN_CENTER);

                document.add(encabezado);

                document.add(new Paragraph(" "));

                Paragraph subtitulo =
                        new Paragraph(
                                "REPORTE DE VENTAS",
                                titulo);

                subtitulo.setAlignment(
                        Paragraph.ALIGN_CENTER);

                document.add(subtitulo);

                document.add(new Paragraph(" "));

                document.add(
                        new Paragraph(
                                "Fecha: "
                                        + reporte.getFechaReporte()));

                document.add(new Paragraph(" "));

                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);

                table.addCell(
                        new PdfPCell(
                                new Paragraph("FECHA")));

                table.addCell(
                        new PdfPCell(
                                new Paragraph("SUCURSAL")));

                table.addCell(
                        new PdfPCell(
                                new Paragraph("VENDEDOR")));

                table.addCell(
                        new PdfPCell(
                                new Paragraph("TOTAL")));

                NumberFormat formato =
                        NumberFormat.getCurrencyInstance(
                                new Locale("es", "CL"));

                for (VentaResponse venta : reporte.getVentas()) {

                table.addCell(
                        venta.getFecha());

                table.addCell(
                        venta.getSucursal()
                                .getNombre());

                table.addCell(
                        venta.getEmpleado()
                                .getNombre());

                table.addCell(
                        formato.format(
                                venta.getTotal()));
                }

                document.add(table);

                document.add(new Paragraph(" "));

                document.add(
                        new Paragraph(
                                "Cantidad Ventas: "
                                        + reporte.getCantidadVentas()));

                document.add(
                        new Paragraph(
                                "Ventas Totales: "
                                        + formato.format(
                                                reporte.getVentasTotales())));

                document.add(
                        new Paragraph(
                                "Promedio Venta: "
                                        + formato.format(
                                                reporte.getPromedioVenta())));

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