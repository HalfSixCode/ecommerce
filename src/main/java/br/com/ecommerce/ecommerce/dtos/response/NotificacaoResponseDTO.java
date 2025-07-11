package br.com.ecommerce.ecommerce.dtos.response;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.ecommerce.ecommerce.models.enums.TipoNotificacao;


public record NotificacaoResponseDTO(
    Long notificacaoId,
    String titulo,
    String mensagem,
    Boolean lida,
    LocalDateTime dataCriacao,
    TipoNotificacao tipoNotificacao,
    String user_id
) {}
