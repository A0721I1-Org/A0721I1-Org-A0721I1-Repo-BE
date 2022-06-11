package projecta07.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.MenuOrderDTO;
import projecta07.model.*;
import projecta07.service.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("menu")
@CrossOrigin("http://localhost:4200/")
public class MenuController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeProductService typeProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    @Autowired
    private TableService tableService;

    /*  Get values for menu page */
    @RequestMapping(value = "current={currentPage}&size={sizePage}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(@PathVariable("currentPage") int currentPage
            , @PathVariable("sizePage") int sizePage) {
        List<Product> products = productService.getProductsWithPagination(currentPage, sizePage);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "product-type", method = RequestMethod.GET)
    public ResponseEntity<List<TypeProduct>> getTypesProduct() {
        List<TypeProduct> typeProducts = typeProductService.getTypesProduct();
        if (typeProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeProducts, HttpStatus.OK);
    }

    /* Get products by product type id */
    @RequestMapping(value = "product-type/{idType}/{currentPage}&{sizePage}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductsByTypeId(@PathVariable("idType") Long idType
            , @PathVariable("currentPage") int currentPage, @PathVariable("sizePage") int sizePage) {
        List<Product> products = productService.getProductsByTypeProductId(idType, currentPage, sizePage);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /* Get Data for table */
    @RequestMapping(value = "table/{idTable}", method = RequestMethod.GET)
    public ResponseEntity<List<MenuOrderDTO>> getMenuOrderDTO(@PathVariable("idTable") Long idTable) {
        List<MenuOrderDTO> menuOrderDTOS = new ArrayList<>();
        MenuOrderDTO menuOrderDTO;

        /* Get order by table id */
        Order order = orderService.getOrderByTableId(idTable);

        /* Get orders detail by order id */
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(order.getIdOrder());
        for (OrderDetail orderDetail : orderDetails) {
            menuOrderDTO = new MenuOrderDTO();
            menuOrderDTO.setOrderId(order.getIdOrder());
            if (orderDetail.getOrder().getIdOrder() == menuOrderDTO.getOrderId()) {
                menuOrderDTO.setNameProduct(orderDetail.getProduct().getNameProduct());
                menuOrderDTO.setQuantity(orderDetail.getNumberProduct());
                menuOrderDTO.setPrice(orderDetail.getProduct().getPriceProduct());
                menuOrderDTO.setTotalPrice(orderDetail.getTotalProduct());
                menuOrderDTO.setProductId(orderDetail.getProduct().getIdProduct());
            }
            menuOrderDTOS.add(menuOrderDTO);
        }
        ;
        return new ResponseEntity<>(menuOrderDTOS, HttpStatus.OK);
    }

    /* Click button Order*/
    @RequestMapping(value = "table/{idTable}/{idEmployee}", method = RequestMethod.POST)
    public ResponseEntity<Order> handleOrder(@PathVariable("idTable") Long idTable, @RequestBody List<MenuOrderDTO> menuOrderDTOInput
            , @PathVariable("idEmployee") Long idEmployee) {
        /* Define object */
        Order orderSaved = new Order();
        OrderDetail orderDetail;
        Order order = new Order();
        Employee employee = employeeService.getEmployeeById(idEmployee);
        Table table = tableService.getTableById(idTable);
        Product product;
        List<OrderDetail> orderDetails = new ArrayList<>();

        /* Calculate total price in order detail */
        double totalPriceOrderDetail = 0;
        double totalPriceOrder;

        for (MenuOrderDTO menuOrderDTO : menuOrderDTOInput) {
            orderDetail = new OrderDetail();
            orderDetail.setNumberProduct(menuOrderDTO.getQuantity());
            /* Get product */
            product = productService.getProductById(menuOrderDTO.getProductId());

            /* set value for order */
            order.setTable(table);
            order.setEmployee(employee);
            order.setDateOrder(LocalDate.now());

            /* not payment yet */
            order.setStatusOrder(false);

            /* set value for order detail */
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);

            /* Save order */
            orderSaved = orderService.saveOrder(order);
            /* Get total price in order detail */
            totalPriceOrderDetail = orderDetail.calculateTotalPriceOrderDetail(orderDetail);
            orderDetail.setTotalProduct(totalPriceOrderDetail);
            orderDetails.add(orderDetail);

            orderDetailService.save(orderDetail);

            /* Set total price for order */
            totalPriceOrder = order.calculateTotalPriceInOrder(orderDetails , orderSaved);
            orderSaved.setTotalOrder(totalPriceOrder);
            /* Update total price for order */
            orderService.saveOrder(orderSaved);
        }

        return new ResponseEntity<>(orderSaved, HttpStatus.OK);
    }

    /* Click button Payment */
    @RequestMapping(value = "table/{idOrder}/payment", method = RequestMethod.PATCH)
    public ResponseEntity<Order> handlePayment(@PathVariable("idOrder") Long idOrder) {
        /* Get order */
        Order order = orderService.getOrderById(idOrder);

        if(order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setStatusOrder(true);
        orderService.saveOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}