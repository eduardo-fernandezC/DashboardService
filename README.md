# DashboardService

Microservicio Spring Boot encargado de consolidar y exponer métricas clave del negocio: KPIs de ventas, productos, vendedores y sucursales.

## Requisitos

- Java 21
- Maven
- Acceso a DataService (microservicio base de datos)
- Variables de entorno para la conexión a otros servicios

## Instalación y ejecución

```bash
mvn clean install
mvn spring-boot:run
```

El servicio se ejecuta por defecto en `http://localhost:8093`.

## Configuración

Define las siguientes variables de entorno antes de iniciar el servicio:

- **KPI_SERVICE_URL** - URL del servicio de KPI para obtener métricas operativas.

## Funcionalidades principales

- Consolidación de KPIs desde múltiples fuentes de datos.
- Cálculo y exposición de métricas de mejores vendedores por sucursal.
- Análisis de desempeño de productos.
- Análisis de desempeño por sucursal.
- Exposición de endpoints REST para consumo desde frontends o dashboards.
- Integración con cliente HTTP reactivo (WebClient) para llamadas a servicios externos.

## Endpoints principales

- `GET /api/v1/dashboard` - Obtiene el dashboard consolidado con todos los KPIs.

## Notas

- El microservicio utiliza WebClient de Spring para comunicaciones HTTP reactivas.
- La configuración del cliente HTTP está centralizada en `WebClientConfig`.
- Los datos se exponen a través de DTOs estructurados para facilitar su consumo.
