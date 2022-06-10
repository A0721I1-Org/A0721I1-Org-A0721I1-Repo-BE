package projecta07.controller;

import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.model.Table;
import projecta07.service.impl.OrderDetailService;
import projecta07.service.impl.OrderService;
import projecta07.service.impl.StatusService;
import projecta07.service.impl.TableService;

import java.util.List;

@RestController
@RequestMapping("/api/table")
@CrossOrigin(origins = "http://localhost:4200")
public class TableController {
    @Autowired
    private TableService tableService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/emptyTable")
    public ResponseEntity<List<Table>> findAllEmptyTable() {
        List<Table> tables = tableService.findAllEmptyTable();
        List<Order> orders = orderService.findAll();

        for (Table table : tables) {
            for (Order or : orders) {
                if (or.getTable().getIdTable() == table.getIdTable() && or != null) {
                    table.setEmptyTable(false);
                } else {
                    table.setEmptyTable(true);
                }
                tableService.save(table);
            }
        }
        if (tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/detailTable/{id}")
    public ResponseEntity<DetailOrderTableDTO> findAllOrderByTableId(@PathVariable Long id) {
        Order order = orderService.findOrderOfTableById(id);
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(order.getIdOrder());

        DetailOrderTableDTO detailOrderTableDTO = new DetailOrderTableDTO();
        for (OrderDetail o : orderDetails) {
            detailOrderTableDTO.setNameProduct(o.getProduct().getNameProduct());
            detailOrderTableDTO.setPriceProduct(o.getProduct().getPriceProduct());
            detailOrderTableDTO.setNumberProduct(o.getNumberProduct());
        }
        detailOrderTableDTO.setCodeTable(order.getTable().getCodeTable());
        detailOrderTableDTO.setTotalOrder(order.getTotalOrder());
        return new ResponseEntity<>(detailOrderTableDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrderInTable/{id}")
    public ResponseEntity<Order> deleteOrderInTable(@PathVariable Long id) {
        Order order = orderService.findOrderOfTableById(id);
        /*List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(order.getIdOrder());*/
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.cancelTable(id);
        List<Table> tables = tableService.findAllEmptyTable();
        for (Table table : tables) {
            table.setEmptyTable(false);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
