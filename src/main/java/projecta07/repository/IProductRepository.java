package projecta07.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.OrderDetail;
@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCodeProductContainingAndNameProductContaining(String codeProduct, String nameProduct);
    Page<Product> findProductByCodeProductContainingAndNameProductContaining(String codeProduct, String nameProduct, Pageable pageable);
    @Query(value = "select * from product limit ?1,?2" , nativeQuery=true)
    List<Product> getProductsWithPagination(int currentPage, int size);

    @Query(value = "select * from product where id_type_product = ?1  limit ?2,?3" , nativeQuery = true)
    List<Product> getProductsByTypeProductId(Long id , int currentPage , int size);

    @Query(value = "select count(product.id_product) from product" , nativeQuery = true)
    int getAmountOfProducts();

    @Query(value = "select count(product.id_product) from product where id_type_product = ?1" , nativeQuery=true)
    int getAmountOfProductsByTypeId(Long id);

    //Nhung
    @Query(value ="SELECT * FROM projecta07.product ORDER BY product.id_product DESC", nativeQuery = true)
    List<Product> findAllProductNew();

    //Nhung
    @Query(value =" select * from product inner join orderdetail on orderdetail.id_product = product.id_product group by product.name_product order by sum(orderdetail.number_product) desc limit 5",nativeQuery = true)
    List<Product> findMostAll();

}
