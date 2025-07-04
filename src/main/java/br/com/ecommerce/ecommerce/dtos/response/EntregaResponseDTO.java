package br.com.ecommerce.ecommerce.dtos.response;
import br.com.ecommerce.ecommerce.models.enums.StatusEntrega;

import java.time.LocalDateTime;
import java.util.UUID;

public record EntregaResponseDTO(
    UUID entregaId,
    String codigoRastreio,
    LocalDateTime dataEnvio,
    LocalDateTime dataEntrega,
    String enderecoEntrega,
    StatusEntrega statusEntrega
) {}
