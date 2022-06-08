package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from product limit ?1,?2" , nativeQuery=true)
    List<Product> getProductsWithPagination(int currentPage, int size);

    @Query(value = "select * from product where id_type_product = ?1  limit ?2,?3" , nativeQuery = true)
    List<Product> getProductsByTypeProductId(Long id , int currentPage , int size);
}
