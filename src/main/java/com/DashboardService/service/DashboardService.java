package com.DashboardService.service;

import org.springframework.stereotype.Service;

import com.DashboardService.client.KpiClient;
import com.DashboardService.dto.DashboardResponse;

@Service
public class DashboardService {

    private final KpiClient kpiClient;

    public DashboardService(KpiClient kpiClient) {
        this.kpiClient = kpiClient;
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
}
