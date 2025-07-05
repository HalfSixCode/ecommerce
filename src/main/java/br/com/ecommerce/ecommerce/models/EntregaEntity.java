package br.com.ecommerce.ecommerce.models;

import br.com.ecommerce.ecommerce.models.enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "DB_ENTREGA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntregaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="entrega_id")
    private UUID entregaId;

    @Column(name= "endereco_entrega")
    private String enderecoEntrega;

    @Column(name="status_entrega", nullable=false)
    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    @Column(name="codigo_rastreamento", nullable = false, length = 40)
    private String codigoRastreio;

    @Column(name= "data_envio",nullable = false)
    private LocalDateTime dataEnvio;

    @Column(name="data_entrega",nullable = false)
    private LocalDateTime dataEntrega;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedidoId;

}
