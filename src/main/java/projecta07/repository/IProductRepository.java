package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCodeProductContainingAndNameProductContaining(String codeProduct, String nameProduct);
    Page<Product> findProductByCodeProductContainingAndNameProductContaining(String codeProduct, String nameProduct, Pageable pageable);
}
