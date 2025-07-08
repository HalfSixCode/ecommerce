package br.com.ecommerce.ecommerce.dtos.request;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.ecommerce.ecommerce.models.enums.TipoNotificacao;
import jakarta.validation.constraints.NotBlank;


public record NotificacaoRequestDTO(
    @NotBlank
    String user_id,
    @NotBlank
    String mensagem,
    String titulo,
    TipoNotificacao tipoNotificacao
) {}
