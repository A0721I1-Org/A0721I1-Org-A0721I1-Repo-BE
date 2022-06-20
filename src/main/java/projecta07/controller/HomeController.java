package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.model.Product;
import projecta07.service.IOrderDetailService;
import projecta07.service.impl.OrderDetailService;
import projecta07.service.impl.OrderService;
import projecta07.service.impl.ProductService;
import projecta07.service.impl.TypeProductService;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("")
@CrossOrigin(origins = "http://localhost:4200")

public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeProductService typeProductService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/find")
    public ResponseEntity<List<Product>> findAllProductNew() {
        List<Product> products = productService.findAllProductNew();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }
    @GetMapping("/cart")
    public ResponseEntity<List<Product>> findMostAll() {
        List<Product> products = productService.findMostAll();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


//    @GetMapping("/home")
//    public ResponseEntity<List<Product>> search(@RequestParam(defaultValue = "")String name){
//        List<Product> products=productService.Search(name);
//        if (products.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }else {
//            return new ResponseEntity<>(products,HttpStatus.OK);
//        }

}

