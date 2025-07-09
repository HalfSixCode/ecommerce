package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.PagamentoEntity;
import br.com.ecommerce.ecommerce.models.enums.FormaPagamento;
import br.com.ecommerce.ecommerce.models.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    // busca pagamento pelo pedido
    Optional<PagamentoEntity> findByPedido_PedidoId(UUID pedidoId);

    // busca por status
    List<PagamentoEntity> findByStatusPagamento(StatusPagamento statusPagamento);

    // busca por forma de pagamento
    List<PagamentoEntity> findByFormaPagamento(FormaPagamento formaPagamento);

}
