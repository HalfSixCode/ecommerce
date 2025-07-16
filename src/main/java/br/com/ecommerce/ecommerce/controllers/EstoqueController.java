package br.com.ecommerce.ecommerce.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.ecommerce.services.EstoqueService;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    
    @Autowired
    private EstoqueService estoqueService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{produtoId}/{quantidade}")
    public ResponseEntity<Void> atualizarEstoque(@PathVariable UUID produtoId,@PathVariable int quantidade) {
        estoqueService.reporEstoque(produtoId, quantidade);
        return ResponseEntity.noContent().build();
    }
}
