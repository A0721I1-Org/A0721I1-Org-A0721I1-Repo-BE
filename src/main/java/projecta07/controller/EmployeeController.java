package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Employee;
import projecta07.service.impl.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
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
}
