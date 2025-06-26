package br.com.ecommerce.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserDTO (
      @NotBlank String name,
      @NotBlank @Email String email,
      @NotBlank @Size(min = 8) String password

) {}

