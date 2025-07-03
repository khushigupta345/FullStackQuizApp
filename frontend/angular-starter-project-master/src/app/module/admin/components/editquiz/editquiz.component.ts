import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-editquiz',
  templateUrl: './editquiz.component.html',
  styleUrls: ['./editquiz.component.css']
})
export class EditquizComponent {

  quizId: number;
  quiz: any = {};  // Holds quiz data

  constructor(
    private route: ActivatedRoute,
    private notification: NzNotificationService,
    private quizService: QuizService,
    private router: Router
  ) {}

  ngOnInit() {
    //  Get quiz ID from route
    this.quizId = +this.route.snapshot.paramMap.get('id');
    console.log("Quiz ID from route:", this.quizId);

    // Load quiz by ID
    this.quizService.getQuizById(this.quizId).subscribe(
      data => {
        this.quiz = data;

        //  Ensure ID is present even if backend doesn't return it
        if (!this.quiz.id) {
          this.quiz.id = this.quizId;
        }

        console.log("Loaded quiz:", this.quiz);
      },
      error => {
        console.error("Error loading quiz:", error);
        this.notification.error("Error", "Unable to load quiz");
        this.router.navigateByUrl('/admin/dashboard');
      }
    );
  }

  // Save updated quiz
  saveUpdatedQuiz() {
    if (!this.quiz.id) {
      alert("Quiz ID missing, cannot update");
      return;
    }

    console.log("Submitting updated quiz:", this.quiz);

    this.quizService.updateQuiz(this.quiz.id, this.quiz).subscribe(
      res => {
        alert("Quiz updated successfully");
        this.router.navigateByUrl('/admin/dashboard');
      },
      error => {
        alert("Update failed");
        console.error("Update error:", error);
        this.router.navigateByUrl('/admin/dashboard');
      }
    );
  }
}
