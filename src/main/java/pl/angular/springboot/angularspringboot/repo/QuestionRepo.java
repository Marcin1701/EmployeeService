package pl.angular.springboot.angularspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.angular.springboot.angularspringboot.model.Employee;
import pl.angular.springboot.angularspringboot.model.Question;

import java.util.Optional;

public interface QuestionRepo extends JpaRepository<Question, Long> {


}
