import { Component } from '@angular/core';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-view-my-result',
  templateUrl: './view-my-result.component.html',
  styleUrls: ['./view-my-result.component.css']
})
export class ViewMyResultComponent {
resultData:any;
constructor( private quizService: QuizService
  ) {}
  ngOnInit(){
    this.getMyResult();
    
  }
  getMyResult(){
    this.quizService.getResultById().subscribe(res=>{
      this.resultData=res;
      console.log(this.resultData);

    })

  }   
}
