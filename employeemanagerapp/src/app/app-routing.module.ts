import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {QuestionListComponent} from "./question-list/question-list.component";

// Przekierowania
const routes: Routes = [
  // :8080/questions
  {path: 'questions', component: QuestionListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// Tablica ze wszsytkimi komponentami
export const routingComponents = [QuestionListComponent];
