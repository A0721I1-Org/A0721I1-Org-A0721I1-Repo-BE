package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.OrderDetail;
import projecta07.model.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    //Nhung
    @Query(value ="SELECT * FROM projecta07.product ORDER BY product.id_product DESC", nativeQuery = true)
    List<Product> findAllProductNew();
    //Nhung
    @Query(value =" select * from product inner join orderdetail on orderdetail.id_product = product.id_product group by product.name_product order by sum(orderdetail.number_product) desc limit 5",nativeQuery = true)
    List<Product> findMostAll();

//    List<Product> findProductByNameProductContaining(String nameProduct);

}
