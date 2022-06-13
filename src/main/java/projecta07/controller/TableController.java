package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Table;
import projecta07.service.ITableService;
import projecta07.service.impl.TableService;
import org.springframework.validation.BindingResult;
import projecta07.dto.TableDTO;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.model.Status;
import projecta07.service.IStatusService;
import projecta07.service.ITableService;
import projecta07.service.impl.OrderDetailService;
import projecta07.service.impl.OrderService;
import projecta07.service.impl.StatusService;
import projecta07.service.impl.TableService;
import projecta07.validate.ValidateTableDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "http://localhost:4200/")
public class TableController {

    @Autowired
    private ValidateTableDTO validateTableDTO;
    @Autowired
    private IStatusService iStatusService;

    @Autowired
    private ITableService iTableService = new TableService();

    @Autowired
    private TableService tableService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    //BinTK
    @GetMapping("/emptyTable")
    public ResponseEntity<List<Table>> findAllEmptyTable() {
        List<Table> tables = tableService.findAllEmptyTable();
        Order order = new Order();

        if (tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Table table : tables) {
                order = orderService.findOrderOfTableById(table.getIdTable());
                if (order == null) {
                    table.setEmptyTable(true);
                    tableService.save(table);
                    continue;
                }
                table.setEmptyTable(false);
                tableService.save(table);
            }
            return new ResponseEntity<>(tables, HttpStatus.OK);
        }
    }

    @PostMapping("/saveOrderInTable")
    public ResponseEntity<Order> saveOrderInTable(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    //BinTK
    //BinTK
    @GetMapping("/emptyTable/detailTable/{id}")
    public ResponseEntity<List<DetailOrderTableDTO>> findAllOrderByTableId(@PathVariable Long id) {

        Order order = orderService.findOrderOfTableById(id);
        List<DetailOrderTableDTO> orderDetailDTOS = new ArrayList<>();
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(order.getIdOrder());

            DetailOrderTableDTO detailOrderTableDTO;
            for (OrderDetail o : orderDetails) {
                detailOrderTableDTO = new DetailOrderTableDTO();
                detailOrderTableDTO.setNameProduct(o.getProduct().getNameProduct());
                detailOrderTableDTO.setPriceProduct(o.getProduct().getPriceProduct());
                detailOrderTableDTO.setNumberProduct(o.getNumberProduct());
                detailOrderTableDTO.setTotalOrder(order.getTotalOrder());
                detailOrderTableDTO.setCodeTable(order.getTable().getCodeTable());
                orderDetailDTOS.add(detailOrderTableDTO);
            }
            return new ResponseEntity<>(orderDetailDTOS, HttpStatus.OK);
        }
    }

    //BinTK
    //BinTK
    @DeleteMapping("/deleteOrderInTable/{idTable}")
    public ResponseEntity<Order> deleteOrderInTable(@PathVariable("idTable") Long id) {
        /* Delete order */
        orderService.cancelTable(id);
        findAllEmptyTable();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //BinTK

    // QuangNV method create Table
    @PostMapping("/createTable")
    public ResponseEntity<?> createTable(@Valid @RequestBody TableDTO tableDTO, BindingResult bindingResult) {
        Boolean check = true;
        validateTableDTO.validate(tableDTO, bindingResult);
        List<Table> tableList = iTableService.findAll();
        for (Integer i = 0; i < tableList.size(); i++) {
            if (tableList.get(i).getCodeTable().equals(tableDTO.getCodeTable())) {
                check = false;
                break;
            }
        }
        if (bindingResult.hasErrors() || !check) {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>("Da ton tai id", HttpStatus.NOT_MODIFIED);
            }
        } else {
            Table table = new Table();
            table.setStatus(tableDTO.getStatus());
            table.setCodeTable(tableDTO.getCodeTable());
            table.setEmptyTable(tableDTO.getEmptyTable());
            return new ResponseEntity<>(iTableService.save(table), HttpStatus.OK);
        }
    }

    //QuangNV method find all status
    @GetMapping("/findAllStatus")
    public ResponseEntity<List<Status>> findAllStatus() {
        List<Status> statusList = iStatusService.findAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(statusList, HttpStatus.OK);
        }
    }

    //QuangNV method find all table
    @GetMapping("/findAllTable")
    public ResponseEntity<Iterable<Table>> findAllTable() {
        List<Table> tables = iTableService.findAll();
        if (tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    // ThaoPTT method update-table
 /*   @GetMapping("/update-table/{id}")
    public ResponseEntity<Table> getTableId(@PathVariable("id") Long id) {
        Table tableOptional = iTableService.getTableById(id);
    }*/

    //HuyNN method find all table with search and paging
    @GetMapping("/findAllTablePaging")
    public ResponseEntity<Iterable<Table>> findAllTablePaging(@RequestParam(value = "codeTable", required = false) Optional<String> codeTable, @RequestParam(value = "idStatus", required = false) Optional<Long> idStatus, @RequestParam(value = "emptyTable", required = false) Optional<Boolean> emptyTable, @PageableDefault(size = 5) Pageable
            pageable) {
        Page<Table> tables;
        Status status = null;
        if (idStatus.isPresent()) {
            status = iStatusService.findStatusById(idStatus.get()).get();
        }
        if (codeTable.isPresent()) {
            if (idStatus.isPresent() && emptyTable.isPresent()) {
                tables = iTableService.findAllByStatusAndEmptyTableAndCodeTable(status, emptyTable.get(), codeTable.get(), pageable);
            } else if (idStatus.isPresent()) {
                tables = iTableService.findAllByStatusAndCodeTable(status, codeTable.get(), pageable);
            } else if (emptyTable.isPresent()) {
                tables = iTableService.findAllByCodeTableAndEmptyTable(codeTable.get(), emptyTable.get(), pageable);
            } else {
                tables = iTableService.findByCodeTable(codeTable.get(), pageable);
            }
        } else if (idStatus.isPresent() && emptyTable.isPresent()) {
            tables = iTableService.findAllByStatusAndEmptyTable(status, emptyTable.get(), pageable);
        } else if (idStatus.isPresent()) {
            tables = iTableService.findAllByStatus(status, pageable);
        } else if (emptyTable.isPresent()) {
            tables = iTableService.findAllByEmptyTable(emptyTable.get(), pageable);
        } else {
            tables = iTableService.findAll(pageable);
        }
        if (tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    //HuyNN method delete table
    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<Table> deleteTableById(@PathVariable Long id) {
        Table table = iTableService.findTableById(id);
        if (table == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTableService.deleteTableById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ThaoPTT method find table by Id
    @GetMapping("/findTableById/{id}")
    public ResponseEntity<Table> findTableById(@PathVariable("id") Long id) {
        Table tableOptional = iTableService.findTableById(id);
        if (tableOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tableOptional, HttpStatus.OK);
    }

    // ThaoPTT method update table
    @PutMapping(value = "/updateTable/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable("id") Long id, @RequestBody Table table) {
        Table tableOptional = iTableService.findTableById(id);
        if (tableOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTableService.updateTable(table);
        Table updatedTable = iTableService.findTableById(id);
        return new ResponseEntity<Table>(updatedTable, HttpStatus.OK);
    }
}

