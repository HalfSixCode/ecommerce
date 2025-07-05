package br.com.ecommerce.ecommerce.dtos.response;
import java.time.LocalDateTime;
import java.util.UUID;


public record NotificacaoResponseDTO(
    UUID notificacaoId,
    UUID usuarioId,
    String mensagem,
    LocalDateTime dataCriacao,
    boolean lida
) {}
