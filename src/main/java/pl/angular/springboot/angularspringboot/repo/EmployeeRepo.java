package pl.angular.springboot.angularspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.angular.springboot.angularspringboot.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    void deleteEmployeeById(Long id);

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> findEmployeeByName(String employeeId);
}
