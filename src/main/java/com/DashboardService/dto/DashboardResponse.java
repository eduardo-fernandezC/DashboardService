package com.DashboardService.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

	private Double ventasTotales;
	private Double cantidadVentas;
	private Double promedioVentas;

	private Double ventasHoy;
	private Double ventasMes;

	private Double cantidadVentasHoy;
	private Double cantidadVentasMes;

	private Double promedioVentasHoy;
	private Double promedioVentasMes;

	private Double crecimientoVentas;

	private ProductoKpi productoMasVendido;
	private ProductoKpi productoMenosVendido;

	private List<MejorVendedorSucursalKpi> mejorVendedorPorSucursal;
	private Map<String, SucursalKpi> rendimientoSucursales;
}
