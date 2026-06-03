package com.DashboardService.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveReportResponse {

    private LocalDate fecha;
    private Double ventasTotales;
    private Double cantidadVentas;
    private String productoMasVendido;
    private String mejorSucursal;
}