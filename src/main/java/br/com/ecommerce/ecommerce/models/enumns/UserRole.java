package br.com.ecommerce.ecommerce.models.enumns;

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
