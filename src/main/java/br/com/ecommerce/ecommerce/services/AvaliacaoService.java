package br.com.ecommerce.ecommerce.services;
import br.com.ecommerce.ecommerce.dtos.request.AvaliacaoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.AvaliacaoResponseDTO;
import br.com.ecommerce.ecommerce.models.AvaliacaoEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import br.com.ecommerce.ecommerce.models.UserEntity;
import br.com.ecommerce.ecommerce.repository.AvaliacaoRepository;
import br.com.ecommerce.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository; // alterar depois

    @Autowired
    private UserService userService;

        public AvaliacaoResponseDTO criarAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO) {
        ProdutoEntity produto = produtoRepository.findById(avaliacaoRequestDTO.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        UserEntity usuario = userService.getUserById(avaliacaoRequestDTO.userId());

        AvaliacaoEntity novaAvaliacao = AvaliacaoEntity.builder()
                .produtoId(produto)
                .userId(usuario)
                .nota(avaliacaoRequestDTO.nota())
                .comentario(avaliacaoRequestDTO.comentario())
                .dataAvaliacao(LocalDateTime.now())
                .build();

        AvaliacaoEntity avaliacaoSalva = avaliacaoRepository.save(novaAvaliacao);

        return new AvaliacaoResponseDTO(
                avaliacaoSalva.getAvaliacaoId(),                    
                avaliacaoSalva.getUserId().getUserId(),          
                avaliacaoSalva.getProdutoId().getProdutoId(),       
                avaliacaoSalva.getComentario(),                     
                avaliacaoSalva.getNota(),                           
                avaliacaoSalva.getDataAvaliacao()                    
        );

    }

    public AvaliacaoResponseDTO buscarAvaliacaoPorId(UUID avaliacaoId) {
        AvaliacaoEntity avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));

        return new AvaliacaoResponseDTO(
                avaliacao.getAvaliacaoId(),
                avaliacao.getUserId().getUserId(),
                avaliacao.getProdutoId().getProdutoId(),
                avaliacao.getComentario(),
                avaliacao.getNota(),
                avaliacao.getDataAvaliacao()
        );
    }

    public List<AvaliacaoResponseDTO> listarAvaliacoesPorProduto(UUID produtoId) {
        ProdutoEntity produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return avaliacaoRepository.findByProdutoId(produtoId).stream()
                .map(avaliacao -> new AvaliacaoResponseDTO(
                        avaliacao.getAvaliacaoId(),
                        avaliacao.getUserId().getUserId(),
                        avaliacao.getProdutoId().getProdutoId(),
                        avaliacao.getComentario(),
                        avaliacao.getNota(),
                        avaliacao.getDataAvaliacao()))
                .toList();
    }

    public void deletarAvaliacao(UUID avaliacaoId) {
        AvaliacaoEntity avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));

        avaliacaoRepository.delete(avaliacao);
    }

    public AvaliacaoResponseDTO buscarAvaliacaoPorUsuario(UUID usuarioId, UUID produtoId) {
        AvaliacaoEntity avaliacao = avaliacaoRepository
                .findByUsuarioIdAndProdutoId(usuarioId, produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
                
        return new AvaliacaoResponseDTO(
                avaliacao.getAvaliacaoId(),
                avaliacao.getUserId().getUserId(),
                avaliacao.getProdutoId().getProdutoId(),
                avaliacao.getComentario(),
                avaliacao.getNota(),
                avaliacao.getDataAvaliacao()
        );        

    }
}