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
    public Optional<EntregaEntity> alterarStatusEntrega(UUID entregaId, StatusEntrega novoStatus) {
        EntregaEntity entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
        
        entrega.setStatusEntrega(novoStatus);
        EntregaEntity entregaAtualizada = entregaRepository.save(entrega);
        
        return Optional.of(entregaAtualizada);
    };
    public void deletarEntrega(UUID entregaId) {
        EntregaEntity entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
        
        entregaRepository.delete(entrega);
    };
    public Optional<EntregaEntity> buscarEntregaPorCodigoRastreio(String codigoRastreio) {
        return entregaRepository.findByCodigoRastreio(codigoRastreio);
    };
    public Optional<EntregaEntity> buscarEntregaPorDataEnvio(LocalDateTime dataEnvio) {
        return entregaRepository.findByDataEnvio(dataEnvio);
    };
    public Optional<EntregaEntity> buscarEntregaPorDataEntrega(LocalDateTime dataEntrega) {
        return entregaRepository.findByDataEntrega(dataEntrega);
    };
    public Optional<EntregaEntity> buscarEntregaPorEndereco(String enderecoEntrega) {
        return entregaRepository.findByEnderecoEntrega(enderecoEntrega);
    };
}
