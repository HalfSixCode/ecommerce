package br.com.ecommerce.ecommerce.controllers;

import br.com.ecommerce.ecommerce.dtos.request.PagamentoRequestDTO;
import br.com.ecommerce.ecommerce.models.PagamentoEntity;
import br.com.ecommerce.ecommerce.models.enums.FormaPagamento;
import br.com.ecommerce.ecommerce.models.enums.StatusPagamento;
import br.com.ecommerce.ecommerce.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoEntity> cadastrarPagamento (@Valid @RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        PagamentoEntity pagamentoSalvo = pagamentoService.salvar(pagamentoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoSalvo);
    }

    @GetMapping("/{pagamentoId}")
    public ResponseEntity<PagamentoEntity> buscarPorId (@PathVariable UUID pagamentoId) {
        PagamentoEntity pagamentoBuscado = pagamentoService.buscarPorId(pagamentoId);

        return ResponseEntity.ok(pagamentoBuscado);
    }

    @GetMapping
    public List<PagamentoEntity> listarPagamento () {
        return pagamentoService.listarPagamentos();
    }

    @GetMapping("/{pedidoId}")
    public PagamentoEntity buscarPagamentoPeloPedido (@PathVariable UUID pedidoId) {
        return pagamentoService.buscarPagamentoPeloPedido(pedidoId);
    }

    @GetMapping("/{statusPedido}")
    public List<PagamentoEntity> buscarPorStatusDoPedido (@PathVariable StatusPagamento statusPagamento) {
        return pagamentoService.buscarPorStatusDoPedido(statusPagamento);
    }

    @GetMapping("/{formaPagamento}")
    public List<PagamentoEntity> listarPorFormaDePagamento (@PathVariable FormaPagamento formaPagamento) {
        return pagamentoService.listarPorFormaDePagamento(formaPagamento);
    }

    @DeleteMapping("/{pagamentoId}")
    public void deletarPorId (@PathVariable UUID pagamentoId) {
        pagamentoService.deletarPorId(pagamentoId);
    }

    @PutMapping("/{pagamentoId}")
    public ResponseEntity<PagamentoEntity> atualizar (
            @PathVariable UUID pagamentoId,
            @RequestBody PagamentoEntity pagamentoEntity
    ) {
        PagamentoEntity pagamentoAtualizado = pagamentoService.atualizar(pagamentoId, pagamentoEntity);

        return ResponseEntity.ok(pagamentoAtualizado);
    }
}
