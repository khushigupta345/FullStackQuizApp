// import { Component } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { RouterTestingHarness } from '@angular/router/testing';
// import { NzMessageService } from 'ng-zorro-antd/message';
// import { NzNotificationService } from 'ng-zorro-antd/notification';
// import { QuizService } from 'src/app/auth/signup/quiz.service';
// import { StorageService } from 'src/app/storage.service';

// @Component({
//   selector: 'app-startquiz',
//   templateUrl: './startquiz.component.html',
//   styleUrls: ['./startquiz.component.css']
// })
// export class StartquizComponent {
// questions :any[]=[];
// testId :any;
// selectedAnswer:{[key:number]:string}={};

// constructor (private ac: ActivatedRoute,
   
//     private quizService: QuizService,private router:Router,private message :NzMessageService){}   
//     ngOnInit(){   
//       this.ac.paramMap.subscribe(param=>{
//         this.testId=+param.get('id');
//         this.quizService.getAllQuestion(this.testId).subscribe(
//           res=>{
            
//             this.questions=res.questions;
//             console.log(this.questions);

//           }
//         )
      
//  } )}
//  onAnswerChange(questionId:number,selectedOption:string){
//   this.selectedAnswer[questionId]=selectedOption;
//   console.log(this.selectedAnswer);

//  }
//  submitAnswer(){
//   const answerList=Object.keys(this.selectedAnswer).map(questionId=>{
//     return {
//       questionId: +questionId,
//       selecteOption: this.selectedAnswer[questionId]

//     }
//   })
//   const data={
//     testId:this.testId,
//     userId:StorageService.getUserId(),

//     response:answerList

//   }
//   console.log(StorageService.getUserId())
//   this.quizService.submitQuiz(data).subscribe(res=>{
// this.message
// .success(
//   `Test submit succcessfully`,
//   {nzDuration:54000}
// );
// this.router.navigateByUrl("/user/view-test-results");

//   },error=>{
//     this.message
//     .error(
//       `${error.error}`,
//       {nzDuration:54000}
//     )
//   })
//   }
// }
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
        
//     console.log(res);
// this.message

// .success(
//   `Test submit succcessfully`,
//   {nzDuration:54000}
// );
// this.router.navigateByUrl("/user/view-test-results");

//   },error=>{
//     this.message
//     .error(
//       `${error.error}`,
//       {nzDuration:54000}
//     )
//   })
  
  // submitAnswer() {
  //   // Check if userId is valid
  //   const userId = StorageService.getUserId();
  //   console.log(userId)
  //   if (!userId) {
  //     this.message.error('User is not logged in');
  //     return; // Stop submission if user is not logged in
  //   }

  //   const answerList = Object.keys(this.selectedAnswer).map((questionId) => {
  //     return {
  //       questionId: +questionId,
  //       selectedOption: this.selectedAnswer[questionId],
  //     };
  //   });

  //   const data = {
  //     testId: this.testId,
  //     userId: userId,
  //     response: answerList,
  //   };

  //   console.log('Submitting answers:', data);
  //   this.quizService.submitQuiz(data).subscribe(
  //     (res) => {
  //       this.message.success(`Test submitted successfully`, { nzDuration: 54000 });
  //       this.router.navigateByUrl('/user/view-test-results');
  //     },
  //     (error) => {
  //       this.message.error(`${error.error}`, { nzDuration: 54000 });
  //     }
  //   );
  // }
  }}