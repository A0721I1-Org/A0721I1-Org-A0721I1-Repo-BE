package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Table;
import projecta07.service.ITableService;
import projecta07.service.impl.TableService;
import org.springframework.validation.BindingResult;
import projecta07.dto.TableDTO;
import projecta07.model.Status;
import projecta07.service.IStatusService;
import projecta07.validate.ValidateTableDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private ValidateTableDTO validateTableDTO;

    @Autowired
    private IStatusService iStatusService;

    @Autowired
    private ITableService iTableService = new TableService();

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

    //HuyNN method getAllTable
    @GetMapping("/getAllTable")
    public ResponseEntity<Iterable<Table>> getAllTable() {
        List<Table> tableList = (List<Table>) iTableService.findAll();
        if (tableList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tableList, HttpStatus.OK);
    }

    //HuyNN method deleteTable
    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<Table> deleteTableById(@PathVariable Long id) {
        Optional<Table> table = iTableService.findTableById(id);
        if (!table.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTableService.deleteTableById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}