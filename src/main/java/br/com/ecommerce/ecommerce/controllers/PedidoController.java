package br.com.ecommerce.ecommerce.controllers;

import br.com.ecommerce.ecommerce.dtos.request.ItemPedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.request.PedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.PedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.enums.StatusPedido;
import br.com.ecommerce.ecommerce.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrarPedido(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO pedido = pedidoService.criarPedido(pedidoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("/{pedidoId}")
    public PedidoResponseDTO buscarPedidoPorId(@PathVariable UUID pedidoId) {
        return pedidoService.buscarPedidoPorId(pedidoId);
    }


    @PutMapping("/alterar-status/{pedidoId}")
    public ResponseEntity<Object> alterarStatusPedido(@PathVariable UUID pedidoId, StatusPedido novoStatus) {
        pedidoService.alterarStatusPedido(pedidoId, novoStatus);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pedidoId}/adicionar-item")
    public ResponseEntity<Void> adicionarItemAoPedido(
            @PathVariable UUID pedidoId,
            @RequestBody @Valid ItemPedidoRequestDTO itemDTO) {

        pedidoService.adicionarItemAoPedido(pedidoId, itemDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{pedidoId}")
    public void deletarPorId (@PathVariable UUID pedidoId) {
        pedidoService.deletarPorId(pedidoId);
    }
}
