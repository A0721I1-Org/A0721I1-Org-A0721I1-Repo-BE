package projecta07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findByAll();
    void deleteById(Long id);
    List<Product> Search(String codeProduct,String nameProduct);
    void createProduct(Product product);
    Optional<Product> findById(Long id);
    Page<Product> findByAllPaging(Pageable pageable);
    Page<Product> searchPage(String codeProduct,String nameProduct,Pageable pageable);

    List<Product> findAllProductNew();

    List<Product> findMostAll();
}