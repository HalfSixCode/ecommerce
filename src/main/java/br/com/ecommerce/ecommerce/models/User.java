package br.com.ecommerce.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import br.com.ecommerce.ecommerce.dtos.request.NewUserDTO;
import br.com.ecommerce.ecommerce.models.enums.UserRole;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String user_id;

    private String name;

    private String email;

    private String password;

    private UserRole role;

    public User(NewUserDTO userDTO) {
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.role = UserRole.CLIENTE; 
    }
}
