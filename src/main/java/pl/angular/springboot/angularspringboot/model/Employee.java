package pl.angular.springboot.angularspringboot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// Różne typy więc Serializable - angular JSON np
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    private String jobTitle;

    private String email;

    private String phone;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private String employeeCode;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    public Employee() {}

    public Employee(final String name, final String jobTitle, final String phone, final String imageUrl, final String employeeCode) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEmployeeCode(final String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(final List<Question> questions) {
        this.questions = questions;
    }
}
