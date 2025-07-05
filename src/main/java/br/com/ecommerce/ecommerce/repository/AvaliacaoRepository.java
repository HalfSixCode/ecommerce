package br.com.ecommerce.ecommerce.repository;
import br.com.ecommerce.ecommerce.models.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, UUID> {
    Optional<AvaliacaoEntity> findById(UUID avaliacaoId);
    Optional<AvaliacaoEntity> findByDataAvaliacao(LocalDateTime dataAvaliacao);
    Optional<AvaliacaoEntity> findByComentario(String comentario);
    Optional<AvaliacaoEntity> findByNota(Integer nota);
    Optional<AvaliacaoEntity> findByUsuarioId(UUID usuarioId);
    Optional<AvaliacaoEntity> findByProdutoId(UUID produtoId);
}
