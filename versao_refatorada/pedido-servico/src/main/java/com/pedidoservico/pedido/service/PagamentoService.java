package com.pedidoservico.pedido.service;

import com.pedidoservico.pedido.model.Pedido;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PagamentoService {

    private final WebClient webClient;

    @Autowired
    public PagamentoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    @CircuitBreaker(name = "pagamento", fallbackMethod = "processarPagamentoFallback")
    @Bulkhead(name = "pagamento", type = Bulkhead.Type.THREADPOOL)
    public Mono<String> processarPagamento(Pedido pedido) {
        // Chamada assíncrona
        return webClient.post()
                .uri("/pagamentos/processar")
                .bodyValue(new PagamentoRequisicao(pedido.getId(), pedido.getValorTotal()))
                .retrieve()
                .bodyToMono(String.class);
    }

    private Mono<String> processarPagamentoFallback(Pedido pedido, Throwable t) {
        System.out.println("Circuit Breaker ativado. Falha no serviço de pagamento. Causa: " + t.getMessage());
        return Mono.just("Pagamento falhou. Tente novamente mais tarde.");
    }

    // Classe interna para simular o DTO de requisição de pagamento
    private static class PagamentoRequisicao {
        private Long pedidoId;
        private Double valor;
        public PagamentoRequisicao(Long pedidoId, Double valor) {
            this.pedidoId = pedidoId;
            this.valor = valor;
        }
        // Getters
        public Long getPedidoId() { return pedidoId; }
        public Double getValor() { return valor; }
    }
}