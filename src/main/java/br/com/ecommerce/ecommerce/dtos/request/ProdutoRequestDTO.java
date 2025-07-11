package br.com.ecommerce.ecommerce.dtos.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoRequestDTO(
    String name,
    BigDecimal preco,
    LocalDate validade,
    Long categoriaId

) {
} 
