package com.pedidoservico.pedido.controller;

import com.pedidoservico.pedido.model.Pedido;
import com.pedidoservico.pedido.repositories.PedidoRepository;
import com.pedidoservico.pedido.service.PagamentoService;
import com.pedidoservico.pedido.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public Mono<ResponseEntity<?>> criarPedido(@RequestBody Pedido pedido) {
        return usuarioService.validarUsuario(pedido.getUsuarioId())
                .flatMap(usuarioValido -> {
                    if (!usuarioValido) {
                        return Mono.just(ResponseEntity.badRequest().body("Usuário não encontrado."));
                    }

                    return pagamentoService.processarPagamento(pedido)
                            .flatMap(pagamentoStatus -> {
                                if (pagamentoStatus.contains("Falha")) {
                                    return Mono.just(ResponseEntity.badRequest().body(pagamentoStatus));
                                }
                                pedido.setStatus("CRIADO");
                                Pedido pedidoSalvo = pedidoRepository.save(pedido);
                                return Mono.just(ResponseEntity.ok(pedidoSalvo));
                            })
                            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Erro interno na criação do pedido.")));
                });
    }
}