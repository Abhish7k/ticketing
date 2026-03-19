package com.ticketing.apigateway.route;

import java.net.URI;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class InventoryServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventory/venue/{venueId}")
                        .or(RequestPredicates.path("/api/v1/inventory/event/{eventId}")),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(URI.create("http://localhost:8080")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceApiDocs() {
        return GatewayRouterFunctions.route("inventory-service-api-docs")
                .route(RequestPredicates.path("/docs/inventoryservice/v3/api-docs"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(URI.create("http://localhost:8080")))
                .filter(setPath("/v3/api-docs"))
                .build();
    }
}
