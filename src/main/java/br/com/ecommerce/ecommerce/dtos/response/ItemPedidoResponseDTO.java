package br.com.ecommerce.ecommerce.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponseDTO(

        UUID itemPedidoId,

        Integer quantidade,

        BigDecimal precoUnitario,

        BigDecimal subtotal,

        UUID produtoId
) {
}
