package br.com.ecommerce.ecommerce.services;
import br.com.ecommerce.ecommerce.dtos.request.NotificacaoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.NotificacaoResponseDTO;
import br.com.ecommerce.ecommerce.models.NotificacaoEntity;
import br.com.ecommerce.ecommerce.models.UserEntity;
import br.com.ecommerce.ecommerce.repository.NotificacaoRepository;
import br.com.ecommerce.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UserRepository userRepository;

    public NotificacaoResponseDTO criarNotificacao(NotificacaoRequestDTO notificacaoRequestDTO) {
        UserEntity usuario = userRepository.findById(notificacaoRequestDTO.user_id().toString())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        NotificacaoEntity novaNotificacao = NotificacaoEntity.builder()
                .titulo(notificacaoRequestDTO.titulo())
                .mensagem(notificacaoRequestDTO.mensagem())
                .lida(false)
                .dataCriacao(LocalDateTime.now())
                .tipoNotificacao(notificacaoRequestDTO.tipoNotificacao())
                .usuarioId(usuario)
                .build();

        NotificacaoEntity notificacaoSalva = notificacaoRepository.save(novaNotificacao);

        return new NotificacaoResponseDTO(
                notificacaoSalva.getNotificacaoId(),
                notificacaoSalva.getTitulo(),
                notificacaoSalva.getMensagem(),
                notificacaoSalva.getLida(),
                notificacaoSalva.getDataCriacao(),
                notificacaoSalva.getTipoNotificacao(),
                notificacaoSalva.getUsuarioId().getUser_id()
        );
    }

    public NotificacaoResponseDTO buscarNotificacaoPorId(Long notificacaoId) {
        NotificacaoEntity notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        return new NotificacaoResponseDTO(
                notificacao.getNotificacaoId(),
                notificacao.getTitulo(),
                notificacao.getMensagem(),
                notificacao.getLida(),
                notificacao.getDataCriacao(),
                notificacao.getTipoNotificacao(),
                notificacao.getUsuarioId().getUser_id()
        );
    }

    public void marcarNotificacaoComoLida(Long notificacaoId) {
        NotificacaoEntity notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        notificacao.setLida(true);
        notificacaoRepository.save(notificacao);
    }

    public void deletarNotificacao(Long notificacaoId) {
        NotificacaoEntity notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        notificacaoRepository.delete(notificacao);
    }

    public Optional<NotificacaoResponseDTO> buscarNotificacaoPorTitulo(String titulo) {
        NotificacaoEntity notificacao = notificacaoRepository.findByTitulo(titulo)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        return Optional.of(new NotificacaoResponseDTO(
                notificacao.getNotificacaoId(),
                notificacao.getTitulo(),
                notificacao.getMensagem(),
                notificacao.getLida(),
                notificacao.getDataCriacao(),
                notificacao.getTipoNotificacao(),
                notificacao.getUsuarioId().getUser_id()
        ));
    }
    public Optional<NotificacaoResponseDTO> buscarNotificacaoPorTipo(String tipoNotificacao) {
        NotificacaoEntity notificacao = notificacaoRepository.findByTipoNotificacao(tipoNotificacao)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        return Optional.of(new NotificacaoResponseDTO(
                notificacao.getNotificacaoId(),
                notificacao.getTitulo(),
                notificacao.getMensagem(),
                notificacao.getLida(),
                notificacao.getDataCriacao(),
                notificacao.getTipoNotificacao(),
                notificacao.getUsuarioId().getUser_id()
        ));
    }
}
