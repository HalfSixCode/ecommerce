package br.com.ecommerce.ecommerce.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ItemPedidoRequestDTO (

        @NotBlank
        UUID produtoId,

        @NotBlank(message = "Informe a quantidade do produto")
        Integer quantiade
) {
}
