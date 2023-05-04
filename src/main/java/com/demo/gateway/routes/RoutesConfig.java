package com.demo.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RoutesConfig {
    @Value("${uri.api.clientes}")
    private String clientesUri;

    @Value("${uri.api.cuentas}")
    private String cuentasUri;

    @Value("${uri.api.clientes2}")
    private String clientesUri2;

  /*version 1  @Bean

    public RouteLocator rutasDeClientes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("all_clientes", r -> r.path("/promerica/todos-los-clientes")
                        .filters(f -> f.rewritePath("/promerica/todos-los-clientes", "/v1/api/cliente/all")
                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLITUD")
                                .addResponseHeader("X-GATEYWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE"))
                        .uri(clientesUri)
                )
                .route("cliente_id", r -> r.path("/promerica/cliente/{id}")
                        .and().method(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT)
                        .filters(f -> f.rewritePath("/promerica/", "/v2/api/")
                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLITUD")
                                .addResponseHeader("X-GATEYWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE"))
                        .uri(clientesUri)
                )
                .build();
    }



    //Versión identifica el canal por medio del Header
    @Bean
    public RouteLocator rutasDeClientes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("all_clientes1", r -> r.path("/promerica/todos-los-clientes")
                        .and().predicate(serverWebExchange -> {
                            HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
                            String canal = headers.get("CANAL").get(0);
                            return canal.equals("WEB");
                        })
                        .filters(f -> f.rewritePath("/promerica/todos-los-clientes", "/v1/api/cliente/all")
                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLITUD")
                                .addResponseHeader("X-GATEYWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE"))
                        .uri(clientesUri)
                )
                .route("all_clientes2", r -> r.path("/promerica/todos-los-clientes")
                        .and().predicate(serverWebExchange -> {
                            HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
                            String canal = headers.get("CANAL").get(0);
                            return canal.equals("MOBILE");
                        })
                        .filters(f -> f.rewritePath("/promerica/todos-los-clientes", "/v1/api/cliente/all")
                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLITUD")
                                .addResponseHeader("X-GATEYWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE"))
                        .uri(clientesUri2)
                )
                .route("cliente_id", r -> r.path("/promerica/cliente/{id}")
                        .and().method(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT)
                        .filters(f -> f.rewritePath("/promerica/", "/v2/api/")
                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY-SOLITUD")
                                .addResponseHeader("X-GATEYWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE"))
                        .uri(clientesUri)
                )
                .build();
    }


    @Bean
    public RouteLocator rutasDeCuentas(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("all_cuentas", r -> r.path("/api/v2/pokemon/ditto")
                        .filters(f -> f.addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY_SOLICITUD")
                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY_RESPUESTA")
                        ).uri(cuentasUri))
                .build();
    }
   */
  @Bean
  public KeyResolver userKeyResolver() {
      return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
  }


}
