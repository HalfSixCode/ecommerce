package br.com.ecommerce.ecommerce.util;

import br.com.ecommerce.ecommerce.dtos.request.ItemPedidoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.ItemPedidoResponseDTO;
import br.com.ecommerce.ecommerce.models.ItemPedidoEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import org.springframework.stereotype.Component;


@Component
public class ItemPedidoMapper {

    public ItemPedidoEntity toEntity(ItemPedidoRequestDTO itemPedidoRequestDTO, ProdutoEntity produtoEntity) {
        return ItemPedidoEntity.builder()
                .produtoId(produtoEntity)
                .quantidade(itemPedidoRequestDTO.quantidade())
                .build();
    }

    public ItemPedidoResponseDTO toResponse (ItemPedidoEntity itemPedidoEntity) {
        return new ItemPedidoResponseDTO(
                itemPedidoEntity.getItemPedidoId(),
                itemPedidoEntity.getQuantidade(),
                itemPedidoEntity.getPrecoUnitario(),
                itemPedidoEntity.getSubtotal(),
                itemPedidoEntity.getProdutoId() != null ? itemPedidoEntity.getProdutoId().getProdutoId() : null
        );
    }
}
