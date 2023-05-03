package com.demo.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {
    @Value("${uri.api.clientes}")
    private String clientesUri;

    @Bean
    public RouteLocator rutasDeClientes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("all_clientes", r -> r.path("/v1/api/cliente/all")
                        .filters(f -> f.addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY_SOLICITUD")
                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY_RESPUESTA")
                        ).uri(clientesUri))
                .build();
    }
}
