package br.com.ecommerce.ecommerce.controllers;
import br.com.ecommerce.ecommerce.dtos.request.NotificacaoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.NotificacaoResponseDTO;
import br.com.ecommerce.ecommerce.services.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping("/criarnotificacao")
    public ResponseEntity<NotificacaoResponseDTO> criarNotificacao(@Valid @RequestBody NotificacaoRequestDTO notificacaoRequestDTO) {
        NotificacaoResponseDTO notificacaoCriada = notificacaoService.criarNotificacao(notificacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoCriada);
    }

    @GetMapping("/{notificacaoId}")
    public ResponseEntity<NotificacaoResponseDTO> buscarNotificacaoPorId(@PathVariable UUID notificacaoId) {
        NotificacaoResponseDTO notificacaoBuscada = notificacaoService.buscarNotificacaoPorId(notificacaoId);
        if(notificacaoBuscada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(notificacaoBuscada);
    }

    @PutMapping("/{notificacaoId}/marcar-como-lida")
    public ResponseEntity<Void> marcarNotificacaoComoLida(@PathVariable UUID notificacaoId) {
        notificacaoService.marcarNotificacaoComoLida(notificacaoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    public List<NotificacaoResponseDTO> listarTodasNotificacoes() {
        return notificacaoService.listarTodasNotificacoes();
    }
    
    @GetMapping("/listarnotificacao/{userId}")
    public List<NotificacaoResponseDTO> listarNotificacoesPorUsuario(@PathVariable UUID userId) {
        return notificacaoService.listarNotificacoesPorUsuario(userId);
    }
    @DeleteMapping("/{notificacaoId}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable UUID notificacaoId) {
        notificacaoService.deletarNotificacao(notificacaoId);
        return ResponseEntity.noContent().build();
    }
}
