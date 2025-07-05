package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, UUID> {
    Optional<ItemPedidoEntity> findById (UUID id);
}
