package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    // busca pagamento pelo pedido
    Optional<PagamentoEntity> findByPedido_PedidoId(UUID pedidoId);

    // busca por status
    Optional<PagamentoEntity> findByStatusPagamento(String statusPagamento);

    // busca por forma de pagamento
    Optional<PagamentoEntity> findByFormaPagamento(String formaPagamento);

}
