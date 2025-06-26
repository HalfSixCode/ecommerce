package br.com.ecommerce.ecommerce.models.Enums;

import lombok.*;

@NoArgsConstructor
public enum UserRole {
    CLIENTE("CLIENTE"),
    ADMIN("ADMIN");

    private String role;
    
    UserRole(String role) {
        this.role = role;
    }
}