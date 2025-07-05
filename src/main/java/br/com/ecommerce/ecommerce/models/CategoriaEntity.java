package br.com.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DB_CATEGORIA")
public class CategoriaEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoriaId;


    @Column(name = "descricao", nullable = false)
    private String descricao;


}
