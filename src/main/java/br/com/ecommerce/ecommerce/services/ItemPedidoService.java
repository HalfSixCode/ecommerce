package br.com.ecommerce.ecommerce.services;

import br.com.ecommerce.ecommerce.dtos.request.ItemPedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.ItemPedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.ItemPedidoEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import br.com.ecommerce.ecommerce.repository.ItemPedidoRepository;
import br.com.ecommerce.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.ecommerce.util.ItemPedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    public ItemPedidoResponseDTO salvar(ItemPedidoRequestDTO dto) {
        ProdutoEntity produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + dto.produtoId() + " não encontrado"));

        ItemPedidoEntity item = itemPedidoMapper.toEntity(dto, produto);
        item.setPrecoUnitario(produto.getPreco());
        item.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(dto.quantidade())));

        ItemPedidoEntity salvo = itemPedidoRepository.save(item);
        return itemPedidoMapper.toResponse(salvo);
    }

    public ItemPedidoResponseDTO buscarPorId(UUID id) {
        ItemPedidoEntity item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do pedido com ID " + id + " não encontrado"));

        return itemPedidoMapper.toResponse(item);
    }

    public List<ItemPedidoResponseDTO> listarTodos() {
        return itemPedidoRepository.findAll().stream()
                .map(itemPedidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deletarPorId(UUID id) {
        if (!itemPedidoRepository.existsById(id)) {
            throw new RuntimeException("Item do pedido com ID " + id + " não encontrado para exclusão");
        }
        itemPedidoRepository.deleteById(id);
    }

    public ItemPedidoResponseDTO atualizar(UUID id, ItemPedidoRequestDTO dto) {
        ItemPedidoEntity existente = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do pedido com ID " + id + " não encontrado"));

        ProdutoEntity produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + dto.produtoId() + " não encontrado"));

        existente.setProdutoId(produto);
        existente.setQuantidade(dto.quantidade());
        existente.setPrecoUnitario(produto.getPreco());
        existente.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(dto.quantidade())));

        ItemPedidoEntity atualizado = itemPedidoRepository.save(existente);
        return itemPedidoMapper.toResponse(atualizado);
    }
}
