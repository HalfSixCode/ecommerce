package br.com.ecommerce.ecommerce.services;

import br.com.ecommerce.ecommerce.models.EstoqueEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import br.com.ecommerce.ecommerce.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
  

    public void inicializarEstoqueParaProduto(ProdutoEntity produto, int quantidadeInicial) {
        if (estoqueRepository.findByProdutoId(produto.getProdutoId()).isPresent()) {
            throw new RuntimeException("Estoque já existe para este produto");
        }

        EstoqueEntity estoque = EstoqueEntity.builder()
                .produtoId(produto)
                .quantidade(quantidadeInicial)
                .ultimaAtualizacao(LocalDateTime.now())
                .build();

        estoqueRepository.save(estoque);
    }


    public void reduzirEstoque(UUID produtoId, int quantidade) {
        EstoqueEntity estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto"));

        int novaQuantidade = estoque.getQuantidade() - quantidade;

        if (novaQuantidade < 0) {
            throw new RuntimeException("Estoque insuficiente para o produto");
        }

        estoque.setQuantidade(novaQuantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
    }

    public void reporEstoque(UUID produtoId, int quantidade) {
        EstoqueEntity estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto"));

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
    }


}

