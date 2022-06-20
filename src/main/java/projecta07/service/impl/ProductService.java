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
    IProductRepository iProductRepository;

    @Override
    public List<Product> findAllProductNew() {
        return iProductRepository.findAllProductNew();
    }

    @Override
    public List<Product> findMostAll() {
        return iProductRepository.findMostAll();
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