package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

}
