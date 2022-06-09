package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Employee;
import projecta07.service.IEmployeeService;
import java.util.List;
import org.springframework.validation.BindingResult;
import projecta07.model.Position;
import projecta07.service.IPositionService;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/manager/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPositionService positionService;
    //VinhTQ
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> showList() {
        List<Employee> employeeList = employeeService.findAll();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }
    //VinhTQ
    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (employeeService.findEmployeeById(id) != null) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //VinhTQ
    @GetMapping("/search/{username}/{name}/{phone}")
    public ResponseEntity<List<Employee>> searchEmployee(@PathVariable String username, @PathVariable String name, @PathVariable String phone) {
        if (username.equals("null")) {
            username = "";
        }
        if (name.equals("null")) {
            name = "";
        }
        if (phone.equals("null")) {
            phone = "";
        }
        List<Employee> employeeList = employeeService.searchEmployee(username, name, phone);
        if (employeeList.isEmpty()) {
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
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
    // HauLST
    @GetMapping("/detail/{idUser}")
    public ResponseEntity<Employee> findByIdUser(@PathVariable Long idUser) {
        Employee employee = employeeService.findEmployeeByIdUser(idUser);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
    }
}
