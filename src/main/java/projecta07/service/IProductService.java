package projecta07.service;

import projecta07.model.Product;

import projecta07.model.Product;
import projecta07.model.Position;


import java.util.List;

public interface IProductService {
    List<Product> findAllProductNew();

    //         List<Product> Search(String nameProduct);
    List<Product> findMostAll();
}

