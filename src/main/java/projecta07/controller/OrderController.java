package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Order;
import projecta07.service.impl.EmployeeService;
import projecta07.service.impl.OrderDetailService;
import projecta07.service.impl.OrderService;

import java.util.List;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllOrder")
    public ResponseEntity<List<Order>> findAllOrder(){
            List<Order> orderList = orderService.findAll();
            if (orderList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderList,HttpStatus.OK);
    }
    @PostMapping("/saveOrder")
    public ResponseEntity<Order> saveNewOrderInTable(@RequestBody Order order){
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }
}
