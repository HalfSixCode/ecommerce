package br.com.ecommerce.ecommerce.util;

import br.com.ecommerce.ecommerce.dtos.request.PagamentoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.PedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.PagamentoEntity;
import br.com.ecommerce.ecommerce.models.PedidoEntity;
import org.springframework.stereotype.Component;


@Component
public class PagamentoMapper {

    public PagamentoEntity toEntity (PagamentoRequestDTO pagamentoRequestDTO, PedidoEntity pedidoEntity) {
        return PagamentoEntity.builder()
                .formaPagamento(pagamentoRequestDTO.formaPagamento())
                .pedidoId(pedidoEntity)
                .build();
    }

    public PedidoResponseDTO toResponse (PedidoEntity pedidoEntity) {
        return new PedidoResponseDTO(
                pedidoEntity.getPedidoId(),
                pedidoEntity.getDataPedido(),
                pedidoEntity.getStatusPedido(),
                pedidoEntity.getValorTotal(),
                pedidoEntity.getUserId().getUserId(),
                pedidoEntity.getItemPedido()
        );
    }
}
