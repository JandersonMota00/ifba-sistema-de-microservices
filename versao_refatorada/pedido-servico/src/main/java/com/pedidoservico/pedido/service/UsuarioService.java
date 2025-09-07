package com.pedidoservico.pedido.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final WebClient webClient;

    @Autowired
    public UsuarioService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    @CircuitBreaker(name = "usuario", fallbackMethod = "validarUsuarioFallback")
    @Bulkhead(name = "usuario", type = Bulkhead.Type.THREADPOOL)
    public Mono<Boolean> validarUsuario(Long usuarioId) {
        // Chamada assíncrona
        return webClient.get()
                .uri("/usuarios/" + usuarioId)
                .retrieve()
                .bodyToMono(Object.class)
                .map(obj -> true)
                .onErrorResume(e -> Mono.just(false));
    }

    private Mono<Boolean> validarUsuarioFallback(Long usuarioId, Throwable t) {
        System.out.println("Circuit Breaker ativado. Falha no serviço de usuário. Causa: " + t.getMessage());
        return Mono.just(false);
    }
}