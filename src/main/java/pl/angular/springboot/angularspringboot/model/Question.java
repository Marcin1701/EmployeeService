package pl.angular.springboot.angularspringboot.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String questionContent;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Question() {

    }

    public Question(final String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(final String questionContent) {
        this.questionContent = questionContent;
    }
}
