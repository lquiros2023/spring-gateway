package com.demo.gateway.circuit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
/* Retorno con error 200
    @GetMapping("/service-fallback")
    public Mono<String> fallbackService() {
        return Mono.just("Por el momento nos encontramos con problemas técnicos, por favor intente en unos minutos.");
    }

 */

//Retorno con error 504
    @GetMapping("/service-fallback")
    public ResponseEntity<String> fallbackService() {
        return new ResponseEntity<>("Por el momento nos encontramos con problemas técnicos, por favor intente en unos minutos.", HttpStatus.GATEWAY_TIMEOUT);
    }
}
