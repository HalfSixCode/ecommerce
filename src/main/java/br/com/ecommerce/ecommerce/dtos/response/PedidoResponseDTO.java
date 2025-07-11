package br.com.ecommerce.ecommerce.dtos.response;

import br.com.ecommerce.ecommerce.models.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO (
        UUID pedidoId,

        LocalDateTime dataPedido,

        StatusPedido statusPedido,

        BigDecimal valorTotal,

        UUID userId,

        //List<ItemPedidoResponseDTO> itens
        List<ItemPedidoResponseDTO> itensResponse){}
