import {Component, OnInit} from '@angular/core';
import {Employee} from "./employee";
import {EmployeeService} from "./employee.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {Question} from "./question-list/question";
import {QuestionService} from "./question-list/question.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;
  public question: string;
  public employeeName: string = "Lee";

  constructor(private employeeService: EmployeeService, private questionService: QuestionService) {
  }

  ngOnInit() {
    // Jeśli skończy się pobieranie
    // Kiedykolowiek onInit się wywoła
    // Wywoła metodę getEmployees() - pobierze dane
    this.getEmployees();
  }

  // Wstrzykiwanie serwisu
  public getEmployees() : void {
    // Wywołanie serwisu - obserwator
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      // Jeśli dostaniemy błąd
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onOpenModal(employee: Employee, mode: string) : void {
    const container = document.getElementById('main-container');
    // Przycisk
    const button = document.createElement('button');
    // Zmieniamy typ z submit na button
    button.type = 'button';
    // Ukryj przycisk
    button.style.display = 'none';
    // mode - który guzik nacisnął użytkownik
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    if (mode === 'addQuestion') {
      this.employeeName = employee.name;
      button.setAttribute("data-target", '#addEmployeeQuestionModal');
    }
    // Dodajemy button do template
    container.appendChild(button);
    button.click();
  }



  public onUpdateEmloyee(employee: Employee): void {
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmloyee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddQuestion(question: string) : void {
    const quest: Question = new class implements Question {
      employee: Employee;
      id: number;
      questionContent: string;
    };
    quest.questionContent = this.question;
    this.questionService.addEmployeeQuestion(quest, this.employeeName).subscribe(
      (response: Question) => {
        this.getEmployees();
       // addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        ////addForm.reset();
      }
    )
  }

  public onAddEmployee(addForm: NgForm) : void{
    document.getElementById('add-employee-form').click();
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public searchEmployees(key: string): void {
    console.log(key);
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }
  }

}
