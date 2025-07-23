package br.com.ecommerce.ecommerce.controllers;
import br.com.ecommerce.ecommerce.dtos.request.EntregaRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.EntregaResponseDTO;
import br.com.ecommerce.ecommerce.models.enums.StatusEntrega;
import br.com.ecommerce.ecommerce.services.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entrega")
public class EntregaController {
    @Autowired
    private EntregaService entregaService;

    @PostMapping("/entrega/criar")
    public ResponseEntity<EntregaResponseDTO> criarEntrega(@Valid @RequestBody EntregaRequestDTO entregaRequestDTO) {
        EntregaResponseDTO entregaCriada = entregaService.criarEntrega(entregaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(entregaCriada);
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaResponseDTO> buscarEntregaPorId(@PathVariable UUID entregaId) {
        EntregaResponseDTO entregaBuscada = entregaService.buscarEntregaPorId(entregaId);
        return ResponseEntity.ok(entregaBuscada);
    }

    @GetMapping("/entrega/listar")
    public List<EntregaResponseDTO> listarEntregas() {
        return entregaService.listarTodasEntregas();
    }
    @DeleteMapping("/{entregaId}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable UUID entregaId) {
        entregaService.deletarEntrega(entregaId);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{entregaId}/status")
    public ResponseEntity<EntregaResponseDTO> atualizarStatusEntrega(
            @PathVariable UUID entregaId,
            @RequestParam StatusEntrega status) {
        EntregaResponseDTO entregaAtualizada = entregaService.alterarStatusEntrega(entregaId, status);
        return ResponseEntity.ok(entregaAtualizada);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<EntregaResponseDTO> buscarEntregasPorPedido(@PathVariable UUID pedidoId) {
        EntregaResponseDTO entrega = entregaService.buscarEntregaPorPedido(pedidoId);
        return ResponseEntity.ok(entrega);
    }

    @GetMapping("/status/{statusEntrega}")
    public ResponseEntity<EntregaResponseDTO> buscarEntregasPorStatus(@PathVariable String statusEntrega) {
        EntregaResponseDTO entrega = entregaService.buscarPorStatus(statusEntrega);
        return ResponseEntity.ok(entrega);
    }

    @GetMapping("/codigo-rastreio/{codigoRastreio}")
    public ResponseEntity<EntregaResponseDTO> buscarEntregaPorCodigoRastreio(@PathVariable String codigoRastreio) {
        EntregaResponseDTO entrega = entregaService.buscarEntregaPorCodigoRastreio(codigoRastreio);
        return ResponseEntity.ok(entrega);
    }
}
