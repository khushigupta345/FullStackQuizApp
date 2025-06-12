import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-view-question',
  templateUrl: './view-question.component.html',
  styleUrls: ['./view-question.component.css']
})
export class ViewQuestionComponent {
questions :any[]=[];
testId :any;
constructor (private ac: ActivatedRoute,
   
    private quizService: QuizService){}   
    ngOnInit(){   
      this.ac.paramMap.subscribe(param=>{
        this.testId=+param.get('id');
        // this.quizService.getAllQuestion(this.testId).subscribe(
          this.quizService.getAllQuestionAdmin(this.testId).subscribe(
          res=>{
            
            this.questions=res.questions;
            console.log(this.questions);

          }
        )
      
 } )}

}