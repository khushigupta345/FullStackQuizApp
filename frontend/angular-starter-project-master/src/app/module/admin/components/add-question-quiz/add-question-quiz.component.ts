import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-add-question-quiz',
  templateUrl: './add-question-quiz.component.html',
  styleUrls: ['./add-question-quiz.component.css']
})
export class AddQuestionQuizComponent implements OnInit {
  validateForm!: FormGroup;
  id: number | null = null;

  constructor(
    private ac: ActivatedRoute,
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router,
    private quizService: QuizService
  ) {}

  ngOnInit(): void {
   
    this.validateForm = this.fb.group({
      questionText: [null, [Validators.required]],
      optionA: [null, [Validators.required]],
      optionB: [null, [Validators.required]],
      optionC: [null, [Validators.required]],
      optionD: [null, [Validators.required]],
      correctOption: [null, [Validators.required]]
    });

   
    this.id = this.ac.snapshot.params['id'] ? +this.ac.snapshot.params['id'] : null;
  }


  submitQuestion(): void {
    if (this.validateForm.invalid) {
     
      this.notification.error('ERROR', 'Please fill in all fields correctly.', { nzDuration: 5000 });
      return;
    }

    const questionDto = this.validateForm.value;
    questionDto.id = this.id;

   
    this.quizService.addQuestion(questionDto).subscribe(
      (res) => {
        
        this.notification.success('SUCCESS', 'Question created successfully',{ nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
      },
      (error) => {
      
        this.notification.error('ERROR', error?.message || 'An error occurred while adding the question.', { nzDuration: 5000 });
      }
    );
  }
}