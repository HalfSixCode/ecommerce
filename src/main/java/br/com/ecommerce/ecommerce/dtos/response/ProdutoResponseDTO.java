package br.com.ecommerce.ecommerce.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProdutoResponseDTO(
    UUID produtoId,
    String name,
    BigDecimal preco,
    LocalDate validade,
    String categoriaNome
) {}
