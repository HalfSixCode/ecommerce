package br.com.ecommerce.ecommerce.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.com.ecommerce.ecommerce.models.enums.TipoNotificacao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DB_NOTIFICACAO")
public class NotificacaoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacao_id")
    private Long notificacaoId;

    @Column(name = "titulo",nullable = false,length = 100)
    private String titulo;

    @Column(name = "mensagem", nullable = false, length = 500)
    private String mensagem;

    @Column(name = "lida", nullable = false)
    private Boolean lida;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "tipo_notificacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipoNotificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuarioId;


}
