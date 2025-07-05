package br.com.ecommerce.ecommerce.dtos.response;
import java.time.LocalDateTime;
import java.util.UUID;


public record AvaliacaoResponseDTO(
    UUID avaliacaoId,
    UUID usuarioId,
    UUID produtoId,
    String comentario,
    int nota,
    LocalDateTime dataCriacao
) {}
