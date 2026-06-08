package com.DashboardService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DashboardService.dto.DashboardResponse;
import com.DashboardService.service.DashboardService;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @Mock
    private DashboardService dashboardService;

    @InjectMocks
    private DashboardController dashboardController;

    @Test
    void getDashboard_retornaDashboard() {

        DashboardResponse dashboard =
                new DashboardResponse();

        when(dashboardService.getDashboard())
                .thenReturn(dashboard);

        DashboardResponse response =
                dashboardController.getDashboard();

        assertEquals(
                dashboard,
                response);

        verify(dashboardService)
                .getDashboard();
    }

}