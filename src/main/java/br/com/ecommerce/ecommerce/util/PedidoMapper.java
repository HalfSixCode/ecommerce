package br.com.ecommerce.ecommerce.util;

import br.com.ecommerce.ecommerce.dtos.request.PedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.PedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.PedidoEntity;
import br.com.ecommerce.ecommerce.models.UserEntity;

import org.springframework.stereotype.Component;


@Component
public class PedidoMapper {

    public PedidoEntity toEntity (PedidoRequestDTO pedidoRequestDTO, UserEntity userEntity) {
        return PedidoEntity.builder()
                .userId(userEntity)
                .itemPedido(pedidoRequestDTO.items())
                .build();
    }

    public PedidoResponseDTO toResponse (PedidoEntity pedidoEntity) {
        return new PedidoResponseDTO(
                pedidoEntity.getPedidoId(),
                pedidoEntity.getDataPedido(),
                pedidoEntity.getStatusPedido(),
                pedidoEntity.getValorTotal(),
                pedidoEntity.getUserId() != null ? pedidoEntity.getUserId().getUserId() : null,
                pedidoEntity.getItemPedido()
        );
    }
}
