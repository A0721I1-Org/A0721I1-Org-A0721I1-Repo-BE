package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import projecta07.dto.TableDTO;
import projecta07.ex.ResourceException;
import projecta07.model.Status;
import projecta07.model.Table;
import projecta07.service.IStatusService;
import projecta07.service.ITableService;
import projecta07.validate.ValidateTableDTO;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "*")
public class TableController {
    @Autowired
    private ValidateTableDTO validateTableDTO;
    @Autowired
    private ITableService iTableService;

    @Autowired
    private IStatusService iStatusService;

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

    //QuangNV method getAllStatus
    @GetMapping("/getAllStatus")
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> statusList = iStatusService.findAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(statusList, HttpStatus.OK);
        }
    }

    // QuangNV method getAllTable
    @GetMapping("/getAllTable")
    public ResponseEntity<List<Table>> getAllTable() {
        List<Table> list = iTableService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // ThaoPTT method update-table
    @GetMapping("/update-table/{id}")
    public ResponseEntity<Table> getTableId(@PathVariable("id") Long id) {
        Table tableOptional = iTableService.getTableById(id);
        return new ResponseEntity<>(tableOptional, HttpStatus.OK);
    }

    /* update table */
    @PutMapping(value = "/update-table/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable("id") Long id, @Valid @RequestBody Table table) {
        Table tableOptional = iTableService.getTableById(id);
        if(table.getStatus().getIdStatus() == null)
        {
            throw new ResourceException(HttpStatus.BAD_REQUEST,"chua nhap status");
        }
        if (tableOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
                iTableService.updateTable(table);
                return new ResponseEntity<Table>(tableOptional, HttpStatus.OK);
            }

}





