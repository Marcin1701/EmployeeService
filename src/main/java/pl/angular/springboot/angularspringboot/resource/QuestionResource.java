package pl.angular.springboot.angularspringboot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.angular.springboot.angularspringboot.model.Question;
import pl.angular.springboot.angularspringboot.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionResource {

    private final QuestionService questionService;

    public QuestionResource(final QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    ResponseEntity<List<Question>> getQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.OK);
    }

    @PostMapping(path="/{name}")
    ResponseEntity<Question> addQuestionToEmployee(@RequestBody Question question,
                                                   @PathVariable("name") String name)
    {
        return new ResponseEntity<>(questionService.addQuestionToUser(question, name), HttpStatus.OK);
    }
}
