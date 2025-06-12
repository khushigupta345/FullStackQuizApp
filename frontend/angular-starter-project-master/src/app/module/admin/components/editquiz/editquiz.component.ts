import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-editquiz',
  templateUrl: './editquiz.component.html',
  styleUrls: ['./editquiz.component.css']
})
export class EditquizComponent {

  quizId: number;
  quiz: any = { };

  constructor(private route: ActivatedRoute,private notification:NzNotificationService, private quizService: QuizService, private router: Router) {}

  ngOnInit() {
    // Fetch the quiz ID from route parameters
    this.quizId = +this.route.snapshot.paramMap.get('id');
    console.log(this.quizId);
    
    // Fetch the quiz data based on quizId
    this.quizService.getQuizById(this.quizId).subscribe(data => {
      this.quiz = data;
      console.log(this.quiz);

    });
  }
  

  // Save updated quiz
  saveUpdatedQuiz() {
    console.log(this.quiz)
    this.quizService.updateQuiz(this.quiz.id, this.quiz).subscribe(
    //   () => {
    //     alert('Quiz updated successfully');
    //     this.router.navigate(['/admin/dashboard']); 
    //   },
    //   () => alert('Failed to update quiz')
    // );

    res => {

 console.log(res)
      // this.notification.success('SUCCESS', 'quiz update successfully', { nzDuration: 5000 });
      this.router.navigateByUrl('/admin/dashboard');
    },
    error => {
      // this.notification.error('ERROR', `${error.error}`, { nzDuration: 5000 });
      this.router.navigateByUrl('/admin/dashboard');
    }
  );
  }


  
}

