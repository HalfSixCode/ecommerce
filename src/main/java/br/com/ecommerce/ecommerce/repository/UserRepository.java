package br.com.ecommerce.ecommerce.repository;


import br.com.ecommerce.ecommerce.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findById(String id);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
