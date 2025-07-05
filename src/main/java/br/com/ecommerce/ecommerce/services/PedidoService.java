package br.com.ecommerce.ecommerce.services;

import br.com.ecommerce.ecommerce.dtos.request.PedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.PedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.PedidoEntity;
import br.com.ecommerce.ecommerce.models.UserEntity;
import br.com.ecommerce.ecommerce.models.enums.StatusPedido;
import br.com.ecommerce.ecommerce.repository.PedidoRepository;
import br.com.ecommerce.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        UserEntity usuario = userRepository.findById(pedidoRequestDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        PedidoEntity novoPedido = PedidoEntity.builder()
                .statusPedido(StatusPedido.ENVIADO)
                .valorTotal(BigDecimal.ZERO) // Vai somar os itens depois
                .userId(usuario)
                .build();

        // VER DEPOIS O ITEM PEDIDO

        PedidoEntity pedidoSalvo = pedidoRepository.save(novoPedido);

        return new PedidoResponseDTO(
                pedidoSalvo.getPedidoId(),
                pedidoSalvo.getDataPedido(),
                pedidoSalvo.getStatusPedido(),
                pedidoSalvo.getValorTotal(),
                pedidoSalvo.getUserId().getUser_id()
                // Mapear a listar de item pedido quando tiver
        );
    }

    public PedidoResponseDTO buscarPedidoPorId(UUID pedidoId) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        return new PedidoResponseDTO(
                pedido.getPedidoId(),
                pedido.getDataPedido(),
                pedido.getStatusPedido(),
                pedido.getValorTotal(),
                pedido.getUserId().getUser_id()
                // Mapear a listar de item pedido quando tiver
        );
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

    /*
    public void adicionarItemAoPedido(UUID pedidoId, ItemPedidoRequestDTO itemDTO) {
        // Lógica de adicionar item ao pedido
    }
    */
}
