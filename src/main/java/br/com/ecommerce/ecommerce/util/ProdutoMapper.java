package br.com.ecommerce.ecommerce.util;

import org.springframework.stereotype.Component;

import br.com.ecommerce.ecommerce.dtos.request.ProdutoRequestDTO;
import br.com.ecommerce.ecommerce.dtos.response.ProdutoResponseDTO;
import br.com.ecommerce.ecommerce.models.CategoriaEntity;
import br.com.ecommerce.ecommerce.models.ProdutoEntity;

@Component
public class ProdutoMapper {

    public ProdutoEntity toEntity(ProdutoRequestDTO dto, CategoriaEntity categoria) {
        return ProdutoEntity.builder()
                .name(dto.name())
                .preco(dto.preco())
                .validade(dto.validade())
                .categoriaId(categoria)
                .build();
    }

    public ProdutoResponseDTO toResponse(ProdutoEntity produto) {
        return new ProdutoResponseDTO(
                produto.getProdutoId(),
                produto.getName(),
                produto.getPreco(),
                produto.getValidade(),
                produto.getCategoriaId().getDescricao()
        );
    }
}
