package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.OrderDetail;
import projecta07.model.Product;
import projecta07.service.IOrderDetailService;
import projecta07.service.IProductService;
import projecta07.service.impl.OrderDetailService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("")
    public ResponseEntity<Iterable<OrderDetail>> findAll() {
        List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailService.findAll();
        if (orderDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println(orderDetails);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable Long id){
        Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
        if(!orderDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(orderDetail.get(),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.save(orderDetail), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDetail> updateBlog(@PathVariable Long id, @RequestBody OrderDetail orderDetail){
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if(!orderDetailOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            //blog khi update khong truyen vao id, thi phai setId can de update
            orderDetail.setIdOrderDetail(orderDetailOptional.get().getIdOrderDetail());
            return new ResponseEntity<>(orderDetailService.save(orderDetail),HttpStatus.OK);
        }
    }

}
