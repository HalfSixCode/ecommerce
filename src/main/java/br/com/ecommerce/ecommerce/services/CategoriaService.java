package br.com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.ecommerce.dtos.request.CategoriaRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.CategoriaResponseDTO;
import br.com.ecommerce.ecommerce.models.CategoriaEntity;
import br.com.ecommerce.ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.ecommerce.util.CategoriaMapper;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private CategoriaMapper categoriaMapper;

     public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        CategoriaEntity categoria = categoriaMapper.toEntity(dto);
        CategoriaEntity salva = categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(salva);
    }

    public CategoriaResponseDTO buscarResponsePorId(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoriaMapper.toResponse(categoria);
    }

     public CategoriaEntity buscarPorId(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoria;
    }

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        CategoriaEntity existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        existente.setDescricao(dto.descricao());
        CategoriaEntity atualizada = categoriaRepository.save(existente);
        return categoriaMapper.toResponse(atualizada);
    }

    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }
}
