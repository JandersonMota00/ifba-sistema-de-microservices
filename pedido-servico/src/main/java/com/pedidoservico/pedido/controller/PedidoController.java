package com.pedidoservico.pedido.controller;

import com.pedidoservico.pedido.model.Pedido;
import com.pedidoservico.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido) {
        // Chamada síncrona e direta para usuario-servico (alto acoplamento)
        ResponseEntity<Object> usuarioResponse = restTemplate.getForEntity(
                "http://localhost:8081/usuarios/" + pedido.getUsuarioId(), Object.class);

        if (!usuarioResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        // Lógica de pagamento duplicada, viola o DRY e o SRP
        // Chamada síncrona e direta para pagamento-servico (alto acoplamento)
        ResponseEntity<String> pagamentoResponse = restTemplate.postForEntity(
                "http://localhost:8082/pagamentos/processar", pedido, String.class);

        if (!pagamentoResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.badRequest().body("Falha no pagamento.");
        }

        pedido.setStatus("CRIADO");
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedidoSalvo);
    }
}