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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UserService userService;

    public NotificacaoResponseDTO criarNotificacao(NotificacaoRequestDTO notificacaoRequestDTO) {
        UserEntity usuario = userService.getUserById(notificacaoRequestDTO.userID());

        NotificacaoEntity novaNotificacao = NotificacaoEntity.builder()
                .userId(usuario)
                .mensagem(notificacaoRequestDTO.mensagem())
                .dataCriacao(LocalDateTime.now())
                .lida(false)
                .build();

        NotificacaoEntity notificacaoSalva = notificacaoRepository.save(novaNotificacao);

        return new NotificacaoResponseDTO(
                notificacaoSalva.getNotificacaoId(),
                notificacaoSalva.getUserId().getUserId(),
                notificacaoSalva.getMensagem(),
                notificacaoSalva.getDataCriacao(),
                notificacaoSalva.getLida()
        );
    }

    public NotificacaoResponseDTO buscarNotificacaoPorId(UUID notificacaoId) {
        NotificacaoEntity notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        return (new NotificacaoResponseDTO(
                notificacao.getNotificacaoId(),
                notificacao.getUserId().getUserId(),
                notificacao.getMensagem(),
                notificacao.getDataCriacao(),
                notificacao.getLida()
        ));
    }

    public void marcarNotificacaoComoLida(UUID notificacaoId) {
        NotificacaoEntity notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        notificacao.setLida(true);
        notificacaoRepository.save(notificacao);
    }

    public void deletarNotificacao(UUID notificacaoId) {
        NotificacaoEntity notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        notificacaoRepository.delete(notificacao);
    }

    public List<NotificacaoResponseDTO> listarNotificacoesPorUsuario(UUID userId) {
        UserEntity usuario = userService.getUserById(userId);
        return notificacaoRepository.findByUsuarioId(userId).stream()
                .map(notificacao -> new NotificacaoResponseDTO(
                        notificacao.getNotificacaoId(),
                        notificacao.getUserId().getUserId(),
                        notificacao.getMensagem(),
                        notificacao.getDataCriacao(),
                        notificacao.getLida()
                )).toList();
    }

    public List<NotificacaoResponseDTO> listarTodasNotificacoes() {
        return notificacaoRepository.findAll().stream()
                .map(notificacao -> new NotificacaoResponseDTO(
                        notificacao.getNotificacaoId(),
                        notificacao.getUserId().getUserId(),
                        notificacao.getMensagem(),
                        notificacao.getDataCriacao(),
                        notificacao.getLida()
                )).toList();
    }

}
