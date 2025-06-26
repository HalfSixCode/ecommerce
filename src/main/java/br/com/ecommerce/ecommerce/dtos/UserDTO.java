package br.com.ecommerce.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDTO (
     @NotBlank String name,
     @NotBlank String email
) {

}