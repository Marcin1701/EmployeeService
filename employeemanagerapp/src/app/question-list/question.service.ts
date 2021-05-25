import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Question} from "./question";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getQuestions() : Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}/question`);
  }

  public addEmployeeQuestion(question: Question, name: string): Observable<any> {
    return this.http.post<Question>(`${this.apiServerUrl}/question/${name}`, question);
  }
}
