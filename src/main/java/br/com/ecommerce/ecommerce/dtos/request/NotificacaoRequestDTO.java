package br.com.ecommerce.ecommerce.dtos.request;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;


public record NotificacaoRequestDTO(
    @NotBlank
    UUID usuarioId,
    @NotBlank
    String mensagem
) {}
