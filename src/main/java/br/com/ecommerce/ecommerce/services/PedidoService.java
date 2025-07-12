package br.com.ecommerce.ecommerce.services;

import br.com.ecommerce.ecommerce.dtos.request.ItemPedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.request.PedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.PedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.ItemPedidoEntity;
import br.com.ecommerce.ecommerce.models.PedidoEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import br.com.ecommerce.ecommerce.models.UserEntity;
import br.com.ecommerce.ecommerce.models.enums.StatusPedido;
import br.com.ecommerce.ecommerce.repository.ItemPedidoRepository;
import br.com.ecommerce.ecommerce.repository.PedidoRepository;
import br.com.ecommerce.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.ecommerce.repository.UserRepository;
import br.com.ecommerce.ecommerce.util.PedidoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        UserEntity usuario = userRepository.findById(pedidoRequestDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        PedidoEntity novoPedido = PedidoEntity.builder()
                .statusPedido(StatusPedido.ENVIADO)
                .valorTotal(BigDecimal.ZERO)
                .userId(usuario)
                .build();

        PedidoEntity pedidoSalvo = pedidoRepository.save(novoPedido);
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoEntity itemDTO : pedidoRequestDTO.items()) {
            ProdutoEntity produto = produtoRepository.findById(itemDTO.getProdutoId().getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

            ItemPedidoEntity item = new ItemPedidoEntity();
            item.setPedidoId(pedidoSalvo);
            item.setProdutoId(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));

            itemPedidoRepository.save(item);
            valorTotal = valorTotal.add(item.getSubtotal());
        }

        pedidoSalvo.setValorTotal(valorTotal);
        pedidoRepository.save(pedidoSalvo);

        List<ItemPedidoEntity> itens = itemPedidoRepository.findByPedidoId(pedidoSalvo);
        pedidoSalvo.setItemPedido(itens);

        return pedidoMapper.toResponse(pedidoSalvo);
    }

    public PedidoResponseDTO buscarPedidoPorId(UUID pedidoId) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        List<ItemPedidoEntity> itens = itemPedidoRepository.findByPedidoId(pedido);
        pedido.setItemPedido(itens);

        return pedidoMapper.toResponse(pedido);
    }

    public void alterarStatusPedido(UUID pedidoId, StatusPedido novoStatus) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedido.setStatusPedido(novoStatus);
        pedidoRepository.save(pedido);
    }

    public void deletarPedido(UUID pedidoId) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedidoRepository.delete(pedido);
    }

    public void adicionarItemAoPedido(UUID pedidoId, ItemPedidoRequestDTO itemDTO) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        ProdutoEntity produto = produtoRepository.findById(itemDTO.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        ItemPedidoEntity novoItem = new ItemPedidoEntity();
        novoItem.setProdutoId(produto);
        novoItem.setPedidoId(pedido);
        novoItem.setQuantidade(itemDTO.quantidade());
        novoItem.setPrecoUnitario(produto.getPreco());
        novoItem.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.quantidade())));

        itemPedidoRepository.save(novoItem);

        BigDecimal novoTotal = itemPedidoRepository.findByPedidoId(pedido).stream()
                .map(ItemPedidoEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorTotal(novoTotal);
        pedidoRepository.save(pedido);
    }

    public void deletarPorId(UUID id) {
        pedidoRepository.deleteById(id);
    }
}
