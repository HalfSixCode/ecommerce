package br.com.ecommerce.ecommerce.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.ecommerce.ecommerce.dtos.response.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DB_AVALIACAO")
public class AvaliacaoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avaliacao_id")
    private UUID avaliacaoId;

    @Column(name= "comentario", nullable = false)
    private String comentario;

    @Column(name= "nota", nullable = false)
    private Integer nota;

    @CreationTimestamp
    @Column(name = "data_avaliacao", nullable = false)
    private LocalDateTime dataAvaliacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produtoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity userId;
}
