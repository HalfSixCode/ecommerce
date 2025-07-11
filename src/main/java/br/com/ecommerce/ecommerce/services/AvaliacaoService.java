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
import java.util.Optional;
import java.util.UUID;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserRepository userRepository;

    public AvaliacaoResponseDTO criarAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO) {
        ProdutoEntity produto = produtoRepository.findById(avaliacaoRequestDTO.produtoId().toString())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));  
    
        UserEntity usuario = userRepository.findById(avaliacaoRequestDTO.user_id().toString())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        AvaliacaoEntity novaAvaliacao = AvaliacaoEntity.builder()
                .produtoId(produto)
                .usuarioId(usuario)
                .dataAvaliacao(LocalDateTime.now())
                .comentario(avaliacaoRequestDTO.comentario())
                .nota(avaliacaoRequestDTO.nota())
                .build();

        AvaliacaoEntity avaliacaoSalva = avaliacaoRepository.save(novaAvaliacao);
    
        return new AvaliacaoResponseDTO(
                avaliacaoSalva.getAvaliacaoId(),
                avaliacaoSalva.getUsuarioId().getUser_id(),
                avaliacaoSalva.getProdutoId().getProdutoId().toString(),
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
                avaliacao.getUsuarioId().getUser_id(),
                avaliacao.getProdutoId().getProdutoId().toString(),
                avaliacao.getComentario(),
                avaliacao.getNota(),
                avaliacao.getDataAvaliacao()
        );
    }
    public void deletarAvaliacao(UUID avaliacaoId) {
        AvaliacaoEntity avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));

        avaliacaoRepository.delete(avaliacao);
    }
    public AvaliacaoResponseDTO atualizarAvaliacao(UUID avaliacaoId, AvaliacaoRequestDTO avaliacaoRequestDTO) {
        AvaliacaoEntity avaliacaoExistente = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));

        ProdutoEntity produto = produtoRepository.findById(avaliacaoRequestDTO.produtoId().toString())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        UserEntity usuario = userRepository.findById(avaliacaoRequestDTO.user_id().toString())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        avaliacaoExistente.setProdutoId(produto);
        avaliacaoExistente.setUsuarioId(usuario);
        avaliacaoExistente.setComentario(avaliacaoRequestDTO.comentario());
        avaliacaoExistente.setNota(avaliacaoRequestDTO.nota());
        avaliacaoExistente.setDataAvaliacao(LocalDateTime.now());

        AvaliacaoEntity avaliacaoAtualizada = avaliacaoRepository.save(avaliacaoExistente);

        return new AvaliacaoResponseDTO(
                avaliacaoAtualizada.getAvaliacaoId(),
                avaliacaoAtualizada.getUsuarioId().getUser_id(),
                avaliacaoAtualizada.getProdutoId().getProdutoId().toString(),
                avaliacaoAtualizada.getComentario(),
                avaliacaoAtualizada.getNota(),
                avaliacaoAtualizada.getDataAvaliacao()
        );
    }
    public Optional<AvaliacaoResponseDTO> buscarAvaliacaoPorProdutoId(UUID produtoId) {
        return avaliacaoRepository.findByProdutoId(produtoId)
                .map(avaliacao -> new AvaliacaoResponseDTO(
                        avaliacao.getAvaliacaoId(),
                        avaliacao.getUsuarioId().getUser_id(),
                        avaliacao.getProdutoId().getProdutoId().toString(),
                        avaliacao.getComentario(),
                        avaliacao.getNota(),
                        avaliacao.getDataAvaliacao()
                ));
    }
}
