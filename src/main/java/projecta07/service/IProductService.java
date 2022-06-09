package projecta07.service;

import projecta07.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProductNew();

    //         List<Product> Search(String nameProduct);
    List<Product> findMostAll();
}

