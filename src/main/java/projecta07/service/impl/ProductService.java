package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Product;
import projecta07.repository.IProductRepository;
import projecta07.service.IProductService;
import java.util.Optional;
import java.util.List;

import java.util.List;

@Service
public class ProductService  implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    IProductRepository iProductRepository;

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

    @Override
    public List<Product> findAllProductNew() {
        return iProductRepository.findAllProductNew();
    }

    @Override
    public List<Product> findMostAll() {
        return iProductRepository.findMostAll();
    }

    @Override
    public void subQuantity(Long idProduct, Integer quantity) {
        iProductRepository.subQuantity(idProduct, quantity);
    }


    /* Get products with pagination */
    public List<Product> getProductsWithPagination(int currentPage , int size) {
        return this.productRepository.getProductsWithPagination(currentPage , size);
    }

    /* Get products by product type id */
    public List<Product> getProductsByTypeProductId(Long id , int currentPage , int size) {
        return this.productRepository.getProductsByTypeProductId(id , currentPage ,  size);
    }

    /* Get amount of products */
    public int getAmountOfProducts() {
        return this.productRepository.getAmountOfProducts();
    }

    /* Get amount of products by id type */
    public int getAmountOfProductsByTypeId(Long id) {
        return this.productRepository.getAmountOfProductsByTypeId(id);
    }

    /* Get product by product id */
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

//    @Override
//    public List<Product> Search(String nameProduct) {
//        return null;
//    }

//
//
//    public List<Product> Search(String nameProduct) {
//        return iProductRepository.findProductByNameProductContaining(nameProduct);
//    }

}
