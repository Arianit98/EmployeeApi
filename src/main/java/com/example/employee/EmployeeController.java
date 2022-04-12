package com.example.employee;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

        private final EmployeeService employeeService;

    private Employee employee=new Employee();

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> showAllEmployees(){
        return employeeService.showAllEmployees();
    }

    @GetMapping(path = "{Id}")
    public Optional<Employee> showEmployee(@PathVariable("Id") Long id){
        return employeeService.showEmployee(id);
    }

    @PostMapping
    public void registerEmployee(@RequestBody Employee employee){
        employeeService.registerEmployee(employee);
    }

    @PutMapping(path = "{id}")
    public void updateEmployee(@PathVariable("id") Long id,@RequestParam String name,@RequestParam String position){
        employeeService.updateEmployee(id,name,position);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
    }

}
