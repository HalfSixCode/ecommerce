package br.com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.ecommerce.dtos.request.ProdutoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.ProdutoResponseDTO;
import br.com.ecommerce.ecommerce.models.CategoriaEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import br.com.ecommerce.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.ecommerce.util.ProdutoMapper;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private ProdutoMapper produtoMapper;
    
    private void saveProduto(ProdutoEntity produto) {
        produtoRepository.save(produto);
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {
        CategoriaEntity categoria = categoriaService.buscarPorId(dto.categoriaId());
            
        ProdutoEntity produto = produtoMapper.toEntity(dto, categoria);
        saveProduto(produto);

        return produtoMapper.toResponse(produto);
    }

     public ProdutoResponseDTO buscarPorId(UUID id) {
        ProdutoEntity produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produtoMapper.toResponse(produto);
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream()
            .map(produtoMapper::toResponse)
            .toList();
    }

    public void deletar(UUID id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizar(UUID id, ProdutoRequestDTO dto) {
        ProdutoEntity produtoExistente = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        CategoriaEntity categoria = categoriaService.buscarPorId(dto.categoriaId());

        produtoExistente.setName(dto.name());
        produtoExistente.setPreco(dto.preco());
        produtoExistente.setValidade(dto.validade());   
        produtoExistente.setCategoriaId(categoria);
       
        saveProduto(produtoExistente);
        
        return produtoMapper.toResponse(produtoExistente);
    }
     

}
