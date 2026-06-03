package com.DashboardService.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesReportResponse {

    private LocalDate fechaReporte;
    private Integer cantidadVentas;
    private Double ventasTotales;
    private Double promedioVenta;
    private List<VentaResponse> ventas;
}