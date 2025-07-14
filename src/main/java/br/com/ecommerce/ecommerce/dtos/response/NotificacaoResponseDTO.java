package br.com.ecommerce.ecommerce.dtos.response;
import java.time.LocalDateTime;
import java.util.UUID;


public record NotificacaoResponseDTO(
    UUID notificacaoId,
    UUID userId,
    String mensagem,
    LocalDateTime dataCriacao,
    boolean lida
) {}
