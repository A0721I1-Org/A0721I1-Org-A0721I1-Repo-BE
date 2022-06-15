package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Product> findByAllPaging(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchPage(String codeProduct, String nameProduct, Pageable pageable) {
        return  iProductRepository.findProductByCodeProductContainingAndNameProductContaining(codeProduct, nameProduct, pageable);
    }

    public void editProduct(Product product){
        iProductRepository.save(product);
    }
}
