package projecta07.service;

import projecta07.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findByAll();
    void deleteById(Long id);
    List<Product> Search(String codeProduct,String nameProduct);
}
