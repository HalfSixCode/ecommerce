package br.com.ecommerce.ecommerce.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO (

        @NotBlank
        UUID userId,

        @NotBlank
        List<ItemPedidoRequestDTO> items
) {}
