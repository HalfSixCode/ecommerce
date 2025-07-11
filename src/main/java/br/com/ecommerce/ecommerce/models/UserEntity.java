package br.com.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import br.com.ecommerce.ecommerce.dtos.request.NewUserDTO;
import br.com.ecommerce.ecommerce.models.enums.UserRole;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DB_USER")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private UserRole role;

    public UserEntity(NewUserDTO userDTO) {
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.role = UserRole.CLIENTE; 
    }
}
