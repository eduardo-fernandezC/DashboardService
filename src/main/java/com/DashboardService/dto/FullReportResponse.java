package com.DashboardService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullReportResponse {

    private ExecutiveReportResponse executive;
    private ProductReportResponse products;
    private SalesReportResponse sales;
}