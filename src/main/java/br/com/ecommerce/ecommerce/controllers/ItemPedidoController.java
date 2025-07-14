package br.com.ecommerce.ecommerce.controllers;

import br.com.ecommerce.ecommerce.dtos.request.ItemPedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.ItemPedidoResponseDTO;
import br.com.ecommerce.ecommerce.services.ItemPedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item-do-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> cadastrar (@Valid @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {
    ItemPedidoResponseDTO itemPedido = itemPedidoService.salvar(itemPedidoRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(itemPedido);
    }

    @GetMapping("/{itemPedidoId}")
    public ResponseEntity<ItemPedidoResponseDTO> buscarItemPedidoPorId (@PathVariable UUID itemPedidoId) {
        ItemPedidoResponseDTO itemPedidoBuscado = itemPedidoService.buscarPorId(itemPedidoId);
        return ResponseEntity.ok(itemPedidoBuscado);
    }

    @GetMapping
    public List<ItemPedidoResponseDTO> listarTodos () {
        return itemPedidoService.listarTodos();
    }

    @DeleteMapping("/{itemPedidoId}")
    public void deletarPorId (@PathVariable UUID itemPedidoId) {
        itemPedidoService.deletarPorId(itemPedidoId);
    }

    @PutMapping("/{itemPedidoId}")
    public ResponseEntity<ItemPedidoResponseDTO> atualizar (
            @PathVariable UUID itemPedidoId,
            @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedidoResponseDTO itemAtualizado = itemPedidoService.atualizar(itemPedidoId, itemPedidoRequestDTO);

        return ResponseEntity.ok(itemAtualizado);
    }

}
