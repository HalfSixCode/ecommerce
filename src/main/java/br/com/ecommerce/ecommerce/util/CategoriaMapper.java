package br.com.ecommerce.ecommerce.util;

import org.springframework.stereotype.Component;

import br.com.ecommerce.ecommerce.dtos.request.CategoriaRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.CategoriaResponseDTO;
import br.com.ecommerce.ecommerce.models.CategoriaEntity;

@Component
public class CategoriaMapper {

    public CategoriaEntity toEntity(CategoriaRequestDTO dto) {
        return CategoriaEntity.builder()
                .descricao(dto.descricao())
                .build();
    }

    public CategoriaResponseDTO toResponse(CategoriaEntity categoria) {
        return new CategoriaResponseDTO(
                categoria.getCategoriaId(),
                categoria.getDescricao()
        );
    }
}