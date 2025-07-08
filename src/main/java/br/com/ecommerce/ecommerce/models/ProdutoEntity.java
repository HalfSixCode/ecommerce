package br.com.ecommerce.ecommerce.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "DB_PROD")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "produto_id")
    private UUID produtoId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "validade", nullable = false)
    private LocalDate validade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoriaId;
}
