package com.DashboardService.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.DashboardService.dto.KpiResponse;
import com.DashboardService.dto.MejorVendedorSucursalKpi;
import com.DashboardService.dto.ProductoKpi;
import com.DashboardService.dto.SucursalKpi;

@Component
public class KpiClient {

    private final WebClient webClient;

    public KpiClient(WebClient.Builder builder,
            @Value("${kpi.service.url:http://localhost:8092}") String kpiServiceUrl) {
        this.webClient = builder
                .baseUrl(kpiServiceUrl)
                .build();
    }

    public Double getVentasTotales() {
        return getKpiValor("/api/v1/kpis/ventas-totales");
    }

    public Double getCantidadVentas() {
        return getKpiValor("/api/v1/kpis/ventas-cantidad");
    }

    public Double getPromedioVentas() {
        return getKpiValor("/api/v1/kpis/ventas-promedio");
    }

    public Double getVentasHoy() {
        return getKpiValor("/api/v1/kpis/ventas-hoy");
    }

    public Double getVentasMes() {
        return getKpiValor("/api/v1/kpis/ventas-mes");
    }

    public Double getCantidadVentasHoy() {
        return getKpiValor("/api/v1/kpis/ventas-hoy-cantidad");
    }

    public Double getCantidadVentasMes() {
        return getKpiValor("/api/v1/kpis/ventas-mes-cantidad");
    }

    public Double getPromedioVentasHoy() {
        return getKpiValor("/api/v1/kpis/ventas-hoy-promedio");
    }

    public Double getPromedioVentasMes() {
        return getKpiValor("/api/v1/kpis/ventas-mes-promedio");
    }

    public Double getCrecimientoVentas() {
        return getKpiValor("/api/v1/kpis/ventas-crecimiento");
    }

    public ProductoKpi getProductoMasVendido() {
        return getBody("/api/v1/kpis/producto-mas-vendido", ProductoKpi.class);
    }

    public ProductoKpi getProductoMenosVendido() {
        return getBody("/api/v1/kpis/producto-menos-vendido", ProductoKpi.class);
    }

    public List<MejorVendedorSucursalKpi> getMejorVendedorPorSucursal() {
        return getBody(
            "/api/v1/kpis/mejor-vendedor-sucursal",
            new ParameterizedTypeReference<List<MejorVendedorSucursalKpi>>() {
            });
    }

    public Map<String, SucursalKpi> getRendimientoSucursales() {
        return getBody(
            "/api/v1/kpis/sucursal-rendimiento",
            new ParameterizedTypeReference<Map<String, SucursalKpi>>() {
            });
    }

    private Double getKpiValor(String uri) {
        KpiResponse response = getBody(uri, KpiResponse.class);

        if (response == null || response.getValor() == null) {
            return 0d;
        }

        return response.getValor();
    }

    private <T> T getBody(String uri, Class<T> bodyType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(bodyType)
                .block();
    }

    private <T> T getBody(String uri, ParameterizedTypeReference<T> bodyType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(bodyType)
                .block();
    }
}