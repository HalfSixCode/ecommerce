package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findById(Long id);
    boolean existsById(Long id);
    
} 
