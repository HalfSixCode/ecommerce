package br.com.ecommerce.ecommerce.controllers;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ecommerce.ecommerce.dtos.request.EntregaRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.EntregaResponseDTO;
import br.com.ecommerce.ecommerce.models.EntregaEntity;
import br.com.ecommerce.ecommerce.models.enums.StatusEntrega;
import br.com.ecommerce.ecommerce.services.EntregaService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {
    @Autowired
    private EntregaService entregaService;

    @PostMapping
    public ResponseEntity<EntregaResponseDTO> criarEntrega(@RequestBody EntregaRequestDTO entregaRequestDTO) {
        EntregaResponseDTO entregaResponse = entregaService.criarEntrega(entregaRequestDTO);
        return new ResponseEntity<>(entregaResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaResponseDTO> buscarEntregaPorId(@PathVariable UUID entregaId) {
        EntregaResponseDTO entregaResponse = entregaService.buscarEntregaPorId(entregaId);
        return ResponseEntity.ok(entregaResponse);
    }

    @DeleteMapping("/{entregaId}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable UUID entregaId) {
        entregaService.deletarEntrega(entregaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{entregaId}/status")
    public ResponseEntity<Void> alterarStatusEntrega(@PathVariable UUID entregaId, @RequestParam StatusEntrega novoStatus) {
        entregaService.alterarStatusEntrega(entregaId, novoStatus);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
