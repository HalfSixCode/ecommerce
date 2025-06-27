package br.com.ecommerce.ecommerce.models;

import br.com.ecommerce.ecommerce.models.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "DB_PED")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pedido_id")
    private UUID pedidoId;

    @CreationTimestamp
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "status_pedido", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User userId;
}
