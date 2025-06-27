package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.PedidoEntity;
import br.com.ecommerce.ecommerce.models.User;
import br.com.ecommerce.ecommerce.models.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {

    Optional<PedidoEntity> findByStatus(StatusPedido statusPedido);

    Optional<PedidoEntity> findByIdAndUser(UUID id, User user);

    Optional<PedidoEntity> findByDataPedidoAndUser(LocalDateTime dataPedido, User user);
    ;
}
