package br.com.ecommerce.ecommerce.models.enums;

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
