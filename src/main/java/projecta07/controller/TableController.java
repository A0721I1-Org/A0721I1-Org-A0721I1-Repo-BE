package projecta07.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Table;
import projecta07.service.ITableService;
import java.util.List;


@RestController
@RequestMapping("/manager")
public class TableController {
    @Autowired
    private ITableService iTableService;

    @GetMapping("/table")
    public ResponseEntity<List<Table>> getAllTable() {
        List<Table> tableList = iTableService.getAll();
        if (tableList.isEmpty()) {
            return new ResponseEntity<List<Table>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Table>>(tableList, HttpStatus.OK);
    }

    @GetMapping("/update-table/{id}")
    public ResponseEntity<Table> getTableId(@PathVariable("id") Long id) {
        Table tableOptional = iTableService.getTableById(id);
        return new ResponseEntity<>(tableOptional, HttpStatus.OK);
    }

    /* update table */
    @PutMapping(value = "/update-table/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable("id") Long id, @RequestBody Table table) {
        Table tableOptional = iTableService.getTableById(id);
        if (tableOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTableService.updateTable(table);
        return new ResponseEntity<Table>(tableOptional, HttpStatus.OK);
    }
}



