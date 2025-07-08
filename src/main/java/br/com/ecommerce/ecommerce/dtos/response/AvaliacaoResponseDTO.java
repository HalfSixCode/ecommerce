package br.com.ecommerce.ecommerce.dtos.response;
import java.time.LocalDateTime;
import java.util.UUID;


public record AvaliacaoResponseDTO(
    long avaliacaoId,
    String user_id,
    String produtoId,
    String comentario,
    int nota,
    LocalDateTime dataCriacao
) {}
