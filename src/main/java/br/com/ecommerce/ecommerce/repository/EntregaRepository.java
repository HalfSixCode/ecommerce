package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface EntregaRepository {
    Optional<EntregaEntity> findById(UUID entregaId);
    Optional<EntregaEntity> findByCodigoRastreio(String codigoRastreio);
    Optional<EntregaEntity> findByDataEnvio(LocalDateTime dataEnvio);
    Optional<EntregaEntity> findByDataEntrega(LocalDateTime dataEntrega);
    Optional<EntregaEntity> findByEnderecoEntrega(String enderecoEntrega);
    Optional<EntregaEntity> findByStatusEntrega(String statusEntrega);
}
