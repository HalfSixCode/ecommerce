package br.com.ecommerce.ecommerce.controllers;
import br.com.ecommerce.ecommerce.dtos.request.AvaliacaoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.AvaliacaoResponseDTO;
import br.com.ecommerce.ecommerce.services.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/avaliacao/criar")
    public ResponseEntity<AvaliacaoResponseDTO> criarAvaliacao(@Valid @RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO) {
        AvaliacaoResponseDTO avaliacaoCriada = avaliacaoService.criarAvaliacao(avaliacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
    }
    @GetMapping("/{avaliacaoId}")
    public ResponseEntity<AvaliacaoResponseDTO> buscarAvaliacaoPorId(@PathVariable UUID avaliacaoId) {
        AvaliacaoResponseDTO avaliacaoBuscada = avaliacaoService.buscarAvaliacaoPorId(avaliacaoId);
        return ResponseEntity.ok(avaliacaoBuscada);
    }
    @GetMapping("/buscaravaliacao")
    public ResponseEntity<AvaliacaoResponseDTO> buscarAvaliacaoPorUsuarioeProduto(@RequestParam UUID userId, @RequestParam UUID produtoId) {
        AvaliacaoResponseDTO avaliacaoBuscada = avaliacaoService.buscarAvaliacaoPorUsuario(userId, produtoId);
        return ResponseEntity.ok(avaliacaoBuscada);
    }
    @DeleteMapping("/{avaliacaoId}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable UUID avaliacaoId) {
        avaliacaoService.deletarAvaliacao(avaliacaoId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/avaliacaoproduto")
    public List<AvaliacaoResponseDTO> listarAvaliacaoProduto(@RequestParam UUID produtoId) {
        return avaliacaoService.listarAvaliacoesPorProduto(produtoId);
    }
    
}
