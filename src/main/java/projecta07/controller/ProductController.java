package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Product;
import projecta07.service.impl.ProductService;
import projecta07.service.impl.TypeProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    TypeProductService typeProductService;
    @GetMapping
    public ResponseEntity<List<Product>> findByAll(){
       List<Product> productList=productService.findByAll();
       if (productList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }else {
           return new ResponseEntity<>(productList,HttpStatus.OK);
       }
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Product> deleteById(@PathVariable(name="id") Long id1){
        productService.deleteById(id1);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(defaultValue = "")String code,@RequestParam(defaultValue = "")String name){
        List<Product> productList=productService.Search(code, name);
        if (productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Product product) {
        productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
