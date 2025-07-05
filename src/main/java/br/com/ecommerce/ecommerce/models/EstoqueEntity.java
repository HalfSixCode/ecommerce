package br.com.ecommerce.ecommerce.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DB_ESTOQUE")
public class EstoqueEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estoqueId;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "ultima_atualização")
    private LocalDateTime ultimaAtualizacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produtoId;
}
