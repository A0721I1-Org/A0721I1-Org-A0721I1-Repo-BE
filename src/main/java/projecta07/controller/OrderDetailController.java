package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.model.OrderDetail;
import projecta07.service.IOrderDetailService;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/order-detail")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody @Valid OrderDetail orderDetail, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailService.save(orderDetail), HttpStatus.CREATED);

    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody @Valid OrderDetail orderDetail, BindingResult bindingResult){
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if(!orderDetailOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            if(bindingResult.hasFieldErrors()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            orderDetail.setIdOrderDetail(orderDetailOptional.get().getIdOrderDetail());
            return new ResponseEntity<>(orderDetailService.save(orderDetail),HttpStatus.OK);
        }
    }

}
