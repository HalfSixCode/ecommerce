package br.com.ecommerce.ecommerce.dtos.response;

import br.com.ecommerce.ecommerce.models.CategoriaEntity;

public record CategoriaResponseDTO(
    
    Long id, 
    String descricao
    
) {

    public CategoriaEntity orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }}