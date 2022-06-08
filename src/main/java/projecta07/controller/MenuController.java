package projecta07.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.MenuOrderDTO;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.model.Product;
import projecta07.model.TypeProduct;
import projecta07.service.impl.OrderDetailService;
import projecta07.service.impl.OrderService;
import projecta07.service.impl.ProductService;
import projecta07.service.impl.TypeProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeProductService typeProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

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
        List<OrderDetail> orderDetails = orderDetailService.getOrdersDetail(order.getIdOrder());
        for(OrderDetail orderDetail: orderDetails) {
            menuOrderDTO = new MenuOrderDTO();
            menuOrderDTO.setOrderId(order.getIdOrder());
            if (orderDetail.getOrder().getIdOrder() == menuOrderDTO.getOrderId()) {
                menuOrderDTO.setNameProduct(orderDetail.getProduct().getNameProduct());
                menuOrderDTO.setQuantity(orderDetail.getNumberProduct());
                menuOrderDTO.setPrice(orderDetail.getProduct().getPriceProduct());
                menuOrderDTO.setTotalPrice(orderDetail.getTotalProduct());
            }
            menuOrderDTOS.add(menuOrderDTO);
        };
        return new ResponseEntity<>(menuOrderDTOS , HttpStatus.OK);
    }
}