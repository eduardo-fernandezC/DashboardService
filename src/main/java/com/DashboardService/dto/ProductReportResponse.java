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
public class ProductReportResponse {

    private LocalDate fecha;
    private Integer totalProductos;
    private List<ProductoResponse> productos;
}