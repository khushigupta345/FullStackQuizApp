

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { QuizService } from './quiz.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private message: NzMessageService,
    private quizService: QuizService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      name: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
  }

  submitForm(): void {
    if (this.validateForm.invalid) {
      this.message.warning('Please fill all required fields correctly!');
      return;
    }

    this.quizService.register(this.validateForm.value).subscribe({
      next: (res) => {
        alert('Signup successful!');
        this.message.success('Signup successful!', { nzDuration: 3000 });
        this.router.navigate(['/login']);
      },
      error: (err) => {
        alert('Signup failed: Bad credentials.');
        this.message.error('Bad Credentials', { nzDuration: 5000 });
      }
    });
  }
}
