package com.pagamentoservico.pagamento.controller;

import com.pagamentoservico.pagamento.model.PagamentoRequisicao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @PostMapping("/processar")
    public String processarPagamento(@RequestBody PagamentoRequisicao requisicao) {
        System.out.println("Processando pagamento para o pedido " + requisicao.getPedidoId() + " com valor " + requisicao.getValor());
        return "Pagamento processado com sucesso!";
    }
}