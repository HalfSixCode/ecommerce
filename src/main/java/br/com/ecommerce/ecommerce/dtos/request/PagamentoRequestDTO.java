package br.com.ecommerce.ecommerce.dtos.request;

import br.com.ecommerce.ecommerce.models.enums.FormaPagamento;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PagamentoRequestDTO(

        @NotBlank(message = "Informa a forma de pagamento")
        FormaPagamento formaPagamento,

        @NotBlank
        UUID pedidoId
) {}
