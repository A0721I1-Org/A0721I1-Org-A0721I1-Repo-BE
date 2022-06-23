package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.TypeProduct;
import projecta07.repository.ITypeProductRepository;
import projecta07.service.ITypeProductService;

import java.util.List;

@Service
public class TypeProductService implements ITypeProductService {
    @Autowired
    private ITypeProductRepository typeProductRepository;

    public List<TypeProduct> getTypesProduct() {
        return typeProductRepository.findAll();
    }
}