package projecta07.service;

import projecta07.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findByAll();
    void deleteById(Long id);
    List<Product> Search(String codeProduct,String nameProduct);
    void createProduct(Product product);
    Optional<Product> findById(Long id);
}
