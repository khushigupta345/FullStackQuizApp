import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-create-test',
  templateUrl: './create-test.component.html',
  styleUrls: ['./create-test.component.css']
})
export class CreateTestComponent {
  testForm!: FormGroup;  // Use the correct name for your form group

  constructor(
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router,
    private quizService: QuizService
  ) {}

  ngOnInit(): void {
  
    this.testForm = this.fb.group({
      title: [null, [Validators.required]], 
      description: [null, [Validators.required]],
      time: [null, [Validators.required]],
    });
  }

  submitForm(): void {
  
    if (this.testForm.valid) {
      this.quizService.createTest(this.testForm.value).subscribe(
        res => {
          this.notification.success('SUCCESS', 'Quiz created successfully', { nzDuration: 5000 });
          this.router.navigateByUrl('/admin/dashboard');
        },
        error => {
          this.notification.error('ERROR', `${error.error}`, { nzDuration: 5000 });
        }
      );
    } else {
     
      this.notification.error('ERROR', 'Please fill all the required fields correctly', { nzDuration: 5000 });
    }
  }
}