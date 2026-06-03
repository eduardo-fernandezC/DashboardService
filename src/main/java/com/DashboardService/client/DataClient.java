package com.DashboardService.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.DashboardService.dto.ProductoResponse;

@Component
public class DataClient {

    private final WebClient webClient;

    public DataClient(
            WebClient.Builder builder,
            @Value("${data.service.url:http://localhost:8091}") String dataServiceUrl) {

        this.webClient = builder
                .baseUrl(dataServiceUrl)
                .build();
    }

    public List<ProductoResponse> getProductos() {

        List<ProductoResponse> productos = webClient.get()
                .uri("/api/v1/productos")
                .retrieve()
                .bodyToFlux(ProductoResponse.class)
                .collectList()
                .block();

        return productos != null
                ? productos
                : List.of();
    }
}