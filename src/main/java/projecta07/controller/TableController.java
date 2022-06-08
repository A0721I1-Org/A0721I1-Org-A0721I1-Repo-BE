package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Table;
import projecta07.service.ITableService;
import projecta07.service.impl.TableService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private ITableService tableService = new TableService();

    @GetMapping("/getAllTable")
    public ResponseEntity<Iterable<Table>> getAllTable() {
        List<Table> tableList = (List<Table>) tableService.findAll();
        if (tableList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tableList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<Table> deleteTableById(@PathVariable Long id) {
        Optional<Table> table = tableService.findTableById(id);
        if (!table.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tableService.deleteTableById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
