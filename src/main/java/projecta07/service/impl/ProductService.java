package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Product;
import projecta07.repository.IProductRepository;
import projecta07.service.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    /* Get products with pagination */
    public List<Product> getProductsWithPagination(int currentPage , int size) {
        return this.productRepository.getProductsWithPagination(currentPage , size);
    }

    /* Get products by product type id */
    public List<Product> getProductsByTypeProductId(Long id , int currentPage , int size) {
        return this.productRepository.getProductsByTypeProductId(id , currentPage ,  size);
    }

    /* Get product by product id */
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }
}