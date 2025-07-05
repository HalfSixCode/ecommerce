package br.com.ecommerce.ecommerce.dtos.request;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record AvaliacaoRequestDTO(
    @NotBlank
    UUID usuarioId,
    @NotBlank
    UUID produtoId,
    @NotBlank
    String comentario,
    int nota
) {}
