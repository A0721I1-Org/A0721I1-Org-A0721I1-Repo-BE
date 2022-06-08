package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Employee;
import projecta07.model.Position;
import projecta07.model.User;
import projecta07.service.IEmployeeService;
import projecta07.service.IPositionService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPositionService positionService;

//    test list
    @GetMapping
    public ResponseEntity<Iterable<Employee>> findAllEmployee() {
        List<Employee> employees = (List<Employee>) employeeService.findAll();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/position")
    public  ResponseEntity<Iterable<Employee>> findAllPosition(){
        List<Position> positions = positionService.listPosition();
        if (positions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(positions, HttpStatus.OK);
    }

    
    @PostMapping(value="/create-employee")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            employeeService.saveEmployee(employee);
//        User user =  findUserByUsername(employee.getUser().getUsername());
//        user.setPassword("123456");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/update-employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@Valid @RequestBody Employee employee,BindingResult bindingResult) {
        Optional<Employee> employeeOptional = employeeService.findByIdEmployee(id);
        if (!employeeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            if (bindingResult.hasFieldErrors()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                employee.setId(employeeOptional.get().getId());
                employeeService.saveEmployee(employee);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    }
}
