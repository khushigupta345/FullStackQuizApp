
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { QuizService } from 'src/app/auth/signup/quiz.service';
import { StorageService } from 'src/app/storage.service';

@Component({
  selector: 'app-startquiz',
  templateUrl: './startquiz.component.html',
  styleUrls: ['./startquiz.component.css']
})
export class StartquizComponent {
  questions: any[] = [];
  testId: any;
  selectedAnswer: { [key: number]: string } = {};
time :number=0;
interval:any;

  constructor(
    private ac: ActivatedRoute,
    private quizService: QuizService,
    private router: Router,
    private storageService:StorageService,
    private message: NzMessageService
  ) {}

  ngOnInit() {
    this.ac.paramMap.subscribe((param) => {
      this.testId = +param.get('id');
      this.quizService.getAllQuestion(this.testId).subscribe(
        (res=> {
          this.questions = res.questions;
          console.log(this.questions);
          console.log(res)
          this.time=res.quizdto.time ||0;
          this.startTimer();

      }),
        (error) => {
          console.error('Error fetching questions:', error);
        }
      );
    });
  }
  startTimer(){
    this.interval=setInterval(()=>{
if(this.time>0){
  this.time--;


}else{
  clearInterval(this.interval);
  this.submitAnswer();

}
    },1000)
  }
getFormattedTime():string{
  const minutes=Math.floor(this.time/60);
  const seconds=this.time/60;
  return `${minutes}:${seconds<10 ?'0':''}${seconds}`;
  
}
  onAnswerChange(questionId: number, selectedOption: string) {
    this.selectedAnswer[questionId] = selectedOption;
    console.log(this.selectedAnswer);
  }
 submitAnswer(){
  const answerList=Object.keys(this.selectedAnswer).map(questionId=>{
    return {
      questionId: +questionId,
      selectedOption: this.selectedAnswer[questionId]

    }
  })
  const data={
    quizId:this.testId,
    userId:this.storageService.getUserId(),

    response:answerList

  }
  console.log(data.userId);
  //console.log("kkkk"+StorageService.getUserId())
  console.log(data);
    this.quizService.submitQuiz(data).subscribe(res=>{
      this.message
      .success(
        `Test submit succcessfully`,
        {nzDuration:54000}
      );
      this.router.navigateByUrl("/user/view-test-results");
      
        },error=>{
          this.message
          .error(
            `${error.error}`,
            {nzDuration:54000}
          )
        })
        

  }}