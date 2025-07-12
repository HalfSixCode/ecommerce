package br.com.ecommerce.ecommerce.dtos.request;

import br.com.ecommerce.ecommerce.models.ItemPedidoEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO (

        @NotBlank
        UUID userId,

        @NotBlank
        List<ItemPedidoEntity> items
) {}
