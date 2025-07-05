package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.models.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    Optional<ProdutoEntity> findById(String id);
    boolean existsById(String id);
    boolean existsByNome(String nome);
    Optional<ProdutoEntity> findByNome(String nome);    
}
