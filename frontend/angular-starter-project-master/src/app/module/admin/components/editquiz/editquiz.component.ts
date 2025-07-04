import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/auth/signup/quiz.service';
import {  OnInit } from '@angular/core';

@Component({
  selector: 'app-editquiz',
  templateUrl: './editquiz.component.html',
  styleUrls: ['./editquiz.component.css']
})
export class EditquizComponent implements OnInit {

  quizId: number;
  quiz: any = {
    title: '',
    description: '',
    time: 0
  };

  constructor(
    private route: ActivatedRoute,
    private quizService: QuizService,
    private router: Router
  ) {}

  ngOnInit() {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.quizId = Number(idParam);
      this.loadQuiz(this.quizId);
console.log("Quiz ID converted = ", this.quizId); 
      
    } else {
      alert('Invalid quiz ID in URL.');
    }
  }

  loadQuiz(id: number) {
    this.quizService.getQuizById(id).subscribe({
      next: (res) => {
        this.quiz = res;
        console.log("Quiz loaded: ", this.quiz);
      },
      error: (err) => {
        console.error("Failed to load quiz", err);
        alert("Unable to load quiz.");
      }
    });
  }

 saveUpdatedQuiz() {
    console.log('Submitting updated quiz:', this.quiz);
   this.quizService.updateQuiz(this.quizId, this.quiz).subscribe({
  next: () => {
    alert("Quiz updated successfully.");
    this.router.navigateByUrl("/admin/dashboard");
  },
  error: (err) => {
    if (err.status === 200) {

      alert("Quiz updated successfully (handled manually).");
      this.router.navigateByUrl("/admin/dashboard");
    } else {
      console.error("Update failed", err);
      alert("Quiz update failed.");
    }
  }
});
  }
}