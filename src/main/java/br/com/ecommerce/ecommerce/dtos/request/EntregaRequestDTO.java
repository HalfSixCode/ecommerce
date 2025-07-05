package br.com.ecommerce.ecommerce.dtos.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record EntregaRequestDTO(
    @NotBlank
    UUID pedidoId,
    @NotBlank
    String enderecoEntrega
) {}
