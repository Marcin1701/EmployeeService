import {Question} from "./question-list/question";

export interface Employee {
  id: number;
  name: string;
  email: string;
  jobTitle: string;
  phone: string;
  imageUrl: string;
  employeeCode: string;
  questions: Question[];
}
