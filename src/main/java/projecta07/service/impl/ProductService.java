package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Product;
import projecta07.repository.IProductRepository;
import projecta07.service.IProductService;
import java.util.Optional;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    /* Get products with pagination */
    public List<Product> getProductsWithPagination(int currentPage , int size) {
        return this.productRepository.getProductsWithPagination(currentPage , size);
    }

    /* Get products by product type id */
    public List<Product> getProductsByTypeProductId(Long id , int currentPage , int size) {
        return this.productRepository.getProductsByTypeProductId(id , currentPage ,  size);
    }
}
