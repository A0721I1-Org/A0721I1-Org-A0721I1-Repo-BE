package projecta07.service;

import projecta07.model.Product;
import projecta07.service.impl.ProductService;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
