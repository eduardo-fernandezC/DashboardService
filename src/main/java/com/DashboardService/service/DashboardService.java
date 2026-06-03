package com.DashboardService.service;

import org.springframework.stereotype.Service;

import com.DashboardService.client.DataClient;
import com.DashboardService.client.KpiClient;
import com.DashboardService.dto.DashboardResponse;

import java.time.LocalDate;

import com.DashboardService.dto.ExecutiveReportResponse;
import com.DashboardService.dto.ProductReportResponse;
import com.DashboardService.dto.ProductoKpi;
import com.DashboardService.dto.SucursalKpi;

@Service
public class DashboardService {

    private final KpiClient kpiClient;
    private final DataClient dataClient;

    public DashboardService(KpiClient kpiClient, DataClient dataClient) {
        this.kpiClient = kpiClient;
        this.dataClient = dataClient;
    }

    public DashboardResponse getDashboard() {
        DashboardResponse response = new DashboardResponse();

        response.setVentasTotales(kpiClient.getVentasTotales());
        response.setCantidadVentas(kpiClient.getCantidadVentas());
        response.setPromedioVentas(kpiClient.getPromedioVentas());

        response.setVentasHoy(kpiClient.getVentasHoy());
        response.setVentasMes(kpiClient.getVentasMes());

        response.setCantidadVentasHoy(kpiClient.getCantidadVentasHoy());
        response.setCantidadVentasMes(kpiClient.getCantidadVentasMes());

        response.setPromedioVentasHoy(kpiClient.getPromedioVentasHoy());
        response.setPromedioVentasMes(kpiClient.getPromedioVentasMes());

        response.setCrecimientoVentas(kpiClient.getCrecimientoVentas());

        response.setProductoMasVendido(kpiClient.getProductoMasVendido());
        response.setProductoMenosVendido(kpiClient.getProductoMenosVendido());

        response.setMejorVendedorPorSucursal(kpiClient.getMejorVendedorPorSucursal());
        response.setRendimientoSucursales(kpiClient.getRendimientoSucursales());

        return response;
    }

    public ExecutiveReportResponse getExecutiveReport() {

        ProductoKpi producto = kpiClient.getProductoMasVendido();

        SucursalKpi mejorSucursal = kpiClient
                .getRendimientoSucursales()
                .get("mayor");

        return new ExecutiveReportResponse(
                LocalDate.now(),
                kpiClient.getVentasTotales(),
                kpiClient.getCantidadVentas(),
                producto != null
                        ? producto.getNombreProducto()
                        : "Sin datos",
                mejorSucursal != null
                        ? mejorSucursal.getSucursal()
                        : "Sin datos");
    }

    public ProductReportResponse getProductsReport() {

        var productos = dataClient.getProductos();

        return new ProductReportResponse(
                java.time.LocalDate.now(),
                productos.size(),
                productos);
    }
}
