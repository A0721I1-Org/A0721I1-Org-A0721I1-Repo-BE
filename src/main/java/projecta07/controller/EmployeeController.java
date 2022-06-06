package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projecta07.model.Employee;
import projecta07.service.IEmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
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
