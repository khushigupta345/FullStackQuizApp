import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-view-question',
  templateUrl: './view-question.component.html',
  styleUrls: ['./view-question.component.css']
})
export class ViewQuestionComponent {
  questions: any[] = [];
  testId: any;
  selectedAnswers: { [key: number]: string } = {};
  submitted: boolean = false;
  score: number = 0;
  pass: boolean = false;

  constructor(
    private ac: ActivatedRoute,
    private quizService: QuizService
  ) {}

  ngOnInit() {
    this.ac.paramMap.subscribe(param => {
      this.testId = +param.get('id');
      this.quizService.getAllQuestionAdmin(this.testId).subscribe(res => {
        this.questions = res.questions;
        console.log(this.questions);
      });
    });
  }

  selectAnswer(questionId: number, option: string) {
    this.selectedAnswers[questionId] = option;
  }

  submitAnswers() {
    this.submitted = true;
    this.score = 0;

    this.questions.forEach(q => {
      if (this.selectedAnswers[q.id] === q.correctOption) {
        this.score++;
      }
    });

    // Optional: Pass/Fail logic (pass if >= 50%)
    this.pass = (this.score / this.questions.length) * 100 >= 50;
  }
}
