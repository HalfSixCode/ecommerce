package br.com.ecommerce.ecommerce.dtos.request;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record AvaliacaoRequestDTO(
    @NotBlank
    String user_id,
    @NotBlank
    UUID produtoId,
    @NotBlank
    String comentario,
    int nota
) {}
