package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Product;
import projecta07.repository.IProductRepository;
import projecta07.service.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository iProductRepository;
    @Override
    public List<Product> findByAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<Product> Search(String codeProduct, String nameProduct) {
        return iProductRepository.findProductByCodeProductContainingAndNameProductContaining(codeProduct, nameProduct);
    }

    public void createProduct(Product product){
        iProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    public void editProduct(Product product){
        iProductRepository.save(product);
    }
}
