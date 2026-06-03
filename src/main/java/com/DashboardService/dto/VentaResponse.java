package com.DashboardService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponse {

    private Long idVenta;
    private String fecha;
    private Double total;
    private SucursalResponse sucursal;
    private EmpleadoResponse empleado;
}