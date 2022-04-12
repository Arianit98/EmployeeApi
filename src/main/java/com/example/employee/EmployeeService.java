package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> showAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> showEmployee(Long id){
        return employeeRepository.findById(id);
    }

    public void registerEmployee(Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findByName(employee.getName());
        if (employeeOptional.isPresent()){
            throw new IllegalStateException("Employee already exists");
        }
        employeeRepository.save(employee);

    }

    @Transactional
    public void updateEmployee(Long id,String name,String position){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new IllegalStateException("Employee with id"+id+"does not exist"));
        if (name!=null && name.length()>0 && !(employee.getName().equals(name))){
            employee.setName(name);
        }
        if (position!=null && position.length()>0 && !(employee.getName().equals(position))){
            employee.setPosition(position);
        }


    }

    public void deleteEmployee(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()){
            throw new IllegalStateException("Employee does not exist");
        }
        employeeRepository.deleteById(id);

    }


}
