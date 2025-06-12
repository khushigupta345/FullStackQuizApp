// import { Component } from '@angular/core';
// import { tick } from '@angular/core/testing';
// import { NzNotificationComponent, NzNotificationService } from 'ng-zorro-antd/notification';
// import { QuizService } from 'src/app/auth/signup/quiz.service';

// @Component({
//   selector: 'app-dashboard',
//   templateUrl: './dashboard.component.html',
//   styleUrls: ['./dashboard.component.css']
// })
// export class DashboardComponent {

//   test=[];
//    constructor( private notification: NzNotificationService,private quizService: QuizService
//     ) {}
//     ngOnInit(){
//       this.get();
//     }
//     get(){
//       this.quizService.getAllQuiz().subscribe(res=>{
//       this.test=res;
//       this.notification
//       .success(`login success`,
        
      
          
//       //       );
//             },eroor=>{
//               this.notification
//               .error(
//                 `ERROR`,
//                 `Something went wrong`,
//                 {nzDuration:54000}
//                 );
              
//             })
//     }
//   }

// getFormatedTime(time):string{
//   const miniut=Math.floor(time/60);
//   const second=time%60;

//   return `${minutes}minutes ${second}seconds`;
// }

// function getFormatedTime(time: any) {
//   throw new Error('Function not implemented.');
// }
import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  quizzes = [];  // Change test to quizzes for consistency.

  constructor(
    private router:Router,
    private notification: NzNotificationService,
    private quizService: QuizService,private message:NzMessageService
  ) {}

  ngOnInit() {
    this.getQuizzes();
    

  }

  getQuizzes() {
    // this.quizService.getAllQuiz().subscribe(
      this.quizService.getAllQuizAdmin().subscribe(
      (res) => {
        this.quizzes = res;
      },
      (error) => {
        this.notification.error(
          'ERROR',
          'Something went wrong',
          { nzDuration: 54000 }
        );
      }
    );
  }

  // Corrected time formatting function
  getFormattedTime(time: number): string {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes} minutes ${seconds} seconds`;
  }


  delete(quizId: number){
    this.quizService.deleteById(quizId).subscribe(
      res=>{
        this.router.navigateByUrl('/admin/dashboard');
      },
      error => {
        // this.notification.error('ERROR', `${error.error}`, { nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
      }
    );
    }
      };
  //       this.message
  //       .success(`delete successfully`,
  //       {
          
  //         // Router.navigate(['/login']),
          
        
  //       }
  //       );
  //           },eroor=>{
  //             this.message
  //             .error(
  //               `Bad Credentials`,
  //               {nzDuration:5000}
  //               );
              
  //           })
          
  // }
