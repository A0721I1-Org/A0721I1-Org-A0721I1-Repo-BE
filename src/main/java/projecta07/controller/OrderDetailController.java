package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.OrderDetail;
import projecta07.service.impl.OrderDetailService;

import java.util.Optional;


@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    //Make by HauNT view detail order
    @RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<OrderDetail>> getOrder(@PathVariable("id") long id) {
        Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
        if (orderDetail == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Optional<OrderDetail>>(orderDetail, HttpStatus.OK);
    }
}
