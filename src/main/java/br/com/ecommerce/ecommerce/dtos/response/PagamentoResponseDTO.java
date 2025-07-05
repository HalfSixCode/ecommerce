package br.com.ecommerce.ecommerce.dtos.response;

import br.com.ecommerce.ecommerce.models.enums.FormaPagamento;
import br.com.ecommerce.ecommerce.models.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoResponseDTO (

        LocalDateTime dataPagamento,

        StatusPagamento statusPagamento,

        FormaPagamento formaPagamento,

        BigDecimal valorPago,

        UUID pedidoId
) {}
