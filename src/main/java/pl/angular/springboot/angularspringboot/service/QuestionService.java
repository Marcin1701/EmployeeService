package pl.angular.springboot.angularspringboot.service;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angular.springboot.angularspringboot.exception.UserNotFoundException;
import pl.angular.springboot.angularspringboot.model.Question;
import pl.angular.springboot.angularspringboot.repo.EmployeeRepo;
import pl.angular.springboot.angularspringboot.repo.QuestionRepo;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    private final EmployeeRepo employeeRepo;

    @Autowired
    public QuestionService(final QuestionRepo questionRepo, final EmployeeRepo employeeRepo) {
        this.questionRepo = questionRepo;
        this.employeeRepo = employeeRepo;
    }

    public Question addQuestion(Question newQuestion) {
        return questionRepo.save(newQuestion);
    }

    public Question getQuestion(Long id) {
        var question = questionRepo.findById(id);
        if (question.isEmpty()){
            throw new IllegalIdentifierException("Question not found!");
        }
        return question.get();
    }

    public Question addQuestionToUser(Question question, String employeeId) {
        var employee = employeeRepo.findEmployeeByName(employeeId);
        if (employee.isEmpty()){
            throw new UserNotFoundException("User with employee id " + employeeId  + " does not exist!");
        } else {
            employee.get().getQuestions().add(question);
            employeeRepo.save(employee.get());
            return question;
        }
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }
}
