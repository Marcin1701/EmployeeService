import {Employee} from "../employee";

export interface Question {
  id: number;
  questionContent: string;
  employee: Employee;
}
