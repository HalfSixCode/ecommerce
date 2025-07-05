package br.com.ecommerce.ecommerce.repository;
import br.com.ecommerce.ecommerce.models.NotificacaoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, UUID> {
    Optional<NotificacaoEntity> findById(UUID notificacaoId);
    Optional<NotificacaoEntity> findByTipoNotificacao(String tipoNotificacao);
    Optional<NotificacaoEntity> findByUsuarioId(UUID usuarioId);
}
