import { Component } from '@angular/core';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-view-result-quiz',
  templateUrl: './view-result-quiz.component.html',
  styleUrls: ['./view-result-quiz.component.css']
})
export class ViewResultQuizComponent {
  resultData:any;
constructor( private quizService: QuizService
  ) {}
  ngOnInit(){
    this.getQuizResult();
    
  }
  getQuizResult(){
    this.quizService.getQuizResult().subscribe(res=>{
      this.resultData=res;
      console.log(this.resultData);

    })

  }   
}
