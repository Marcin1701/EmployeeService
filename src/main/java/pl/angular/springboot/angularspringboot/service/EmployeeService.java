package pl.angular.springboot.angularspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angular.springboot.angularspringboot.exception.UserNotFoundException;
import pl.angular.springboot.angularspringboot.model.Employee;
import pl.angular.springboot.angularspringboot.model.Question;
import pl.angular.springboot.angularspringboot.repo.EmployeeRepo;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        var employee = employeeRepo.findEmployeeById(id);
        if (!employee.isPresent()){
            throw new UserNotFoundException("User by id " + id + " was not found");
        }
        return employee.get();
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
}
