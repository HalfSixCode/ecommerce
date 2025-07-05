package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {
    Optional<EstoqueEntity> findById(Long id);
    boolean existsById(Long id);
    Optional<EstoqueEntity> findByProdutoId(String produtoId);
    
}
