import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { SharedModule } from 'src/app/module/shared/shared.module';
import { QuizService } from './quiz.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

// color=2;
// handle(v:number){
//   this.color=v
// }
// ha(event:Event){
//   this.color=[(event.target as HTMLElement).value];
// }
  
  constructor(private fb:FormBuilder ,private message:NzMessageService,private router :Router,private quizsevice:QuizService){}
  validateForm!:FormGroup;
  ngOnInit(){
    this.validateForm=this.fb.group({
      email:[null,[Validators.required,Validators.email]],
      name:[null,[Validators.required]],
      password:[null,[Validators.required]]
    })
  }

  submitForm(){
    this.quizsevice.register(this.validateForm.value).subscribe(res=>{
this.message
.success(`signup success`,
{
  
  // Router.navigate(['/login']),
  

}
);
    },eroor=>{
      this.message
      .error(
        `Bad Credentials`,
        {nzDuration:5000}
        );
      
    })
  }
  }
