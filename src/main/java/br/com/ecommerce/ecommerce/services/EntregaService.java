package br.com.ecommerce.ecommerce.services;
import br.com.ecommerce.ecommerce.dtos.request.EntregaRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.EntregaResponseDTO;
import br.com.ecommerce.ecommerce.models.EntregaEntity;
import br.com.ecommerce.ecommerce.models.PedidoEntity;
import br.com.ecommerce.ecommerce.models.enums.StatusEntrega;
import br.com.ecommerce.ecommerce.repository.EntregaRepository;
import br.com.ecommerce.ecommerce.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
    
    public EntregaResponseDTO criarEntrega(EntregaRequestDTO entregaRequestDTO) {
        PedidoEntity pedido = pedidoRepository.findById(entregaRequestDTO.pedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        
        EntregaEntity novaEntrega = EntregaEntity.builder()
                .pedidoId(pedido)
                .enderecoEntrega(entregaRequestDTO.enderecoEntrega())
                .statusEntrega(StatusEntrega.PENDENTE)
                .build();
        
        EntregaEntity entregaSalva = entregaRepository.save(novaEntrega);
        
        return new EntregaResponseDTO(
                entregaSalva.getEntregaId(),
                entregaSalva.getPedidoId().getPedidoId(),
                entregaSalva.getCodigoRastreio(),
                entregaSalva.getDataEnvio(),
                entregaSalva.getDataEntrega(),
                entregaSalva.getEnderecoEntrega(),
                entregaSalva.getStatusEntrega()
        );
    };
    public EntregaResponseDTO buscarEntregaPorId(UUID entregaId) {
        EntregaEntity entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
        
        return new EntregaResponseDTO(
                entrega.getEntregaId(),
                entrega.getPedidoId().getPedidoId(),
                entrega.getCodigoRastreio(),
                entrega.getDataEnvio(),
                entrega.getDataEntrega(),
                entrega.getEnderecoEntrega(),
                entrega.getStatusEntrega()
        );
    };
    public EntregaResponseDTO alterarStatusEntrega(UUID entregaId, StatusEntrega status) {
        EntregaEntity entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));

        entrega.setStatusEntrega(status);
        EntregaEntity entregaAtualizada = entregaRepository.save(entrega);

        return new EntregaResponseDTO(
                entregaAtualizada.getEntregaId(),
                entregaAtualizada.getPedidoId().getPedidoId(),
                entregaAtualizada.getCodigoRastreio(),
                entregaAtualizada.getDataEnvio(),
                entregaAtualizada.getDataEntrega(),
                entregaAtualizada.getEnderecoEntrega(),
                entregaAtualizada.getStatusEntrega()
        );
    }

    public void deletarEntrega(UUID entregaId) {
        EntregaEntity entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
        
        entregaRepository.delete(entrega);
    };

    public EntregaResponseDTO buscarEntregaPorCodigoRastreio(String codigoRastreio) {
        EntregaEntity entrega = entregaRepository.findByCodigoRastreio(codigoRastreio)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada com o código de rastreio: " + codigoRastreio));

        return new EntregaResponseDTO(
                entrega.getEntregaId(),
                entrega.getPedidoId().getPedidoId(),
                entrega.getCodigoRastreio(),
                entrega.getDataEnvio(),
                entrega.getDataEntrega(),
                entrega.getEnderecoEntrega(),
                entrega.getStatusEntrega()
        );
    }

    public EntregaResponseDTO buscarPorStatus(String status){
        return entregaRepository.findByStatusEntrega(status)
                .map(entrega -> new EntregaResponseDTO(
                        entrega.getEntregaId(),
                        entrega.getPedidoId().getPedidoId(),
                        entrega.getCodigoRastreio(),
                        entrega.getDataEnvio(),
                        entrega.getDataEntrega(),
                        entrega.getEnderecoEntrega(),
                        entrega.getStatusEntrega()
                ))
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada com o status: " + status));
    }

    public EntregaResponseDTO buscarEntregaPorPedido(UUID pedidoId) {
        return entregaRepository.findByPedidoId(pedidoId)
                .map(entrega -> new EntregaResponseDTO(
                        entrega.getEntregaId(),
                        entrega.getPedidoId().getPedidoId(),
                        entrega.getCodigoRastreio(),
                        entrega.getDataEnvio(),
                        entrega.getDataEntrega(),
                        entrega.getEnderecoEntrega(),
                        entrega.getStatusEntrega()
                ))
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
    }

    public List<EntregaResponseDTO> listarTodasEntregas() {
        return entregaRepository.findAll().stream()
                .map(entrega -> new EntregaResponseDTO(
                        entrega.getEntregaId(),
                        entrega.getPedidoId().getPedidoId(),
                        entrega.getCodigoRastreio(),
                        entrega.getDataEnvio(),
                        entrega.getDataEntrega(),
                        entrega.getEnderecoEntrega(),
                        entrega.getStatusEntrega()
                )).toList();
    }
}
