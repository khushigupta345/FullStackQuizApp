import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { StorageService } from 'src/app/storage.service';


@Injectable({
  providedIn: 'root'
})
export class QuizService {

    private apiUrl = 'http://localhost:8012';
    

  constructor(private http: HttpClient, private storageService: StorageService) {}

register(data: any):Observable<any>{

  return this.http.post(`${this.apiUrl}/signup`,data,{responseType:'text'}); 
}



login(login: any):Observable<any>{
  return this.http.post(`${this.apiUrl}/login`,login); 
}
createTest(quizDTO):Observable<any>{
  return this.http.post(`${this.apiUrl}/api/admin/test`,quizDTO)
}

getAllQuiz():Observable<any>{
  return this.http.get(`${this.apiUrl}/api/user`)
}
getAllQuizAdmin():Observable<any>{
  return this.http.get(`${this.apiUrl}/api/admin`)
}

addQuestion(questionDto ): Observable<any> {
  return this.http.post(`${this.apiUrl}/api/admin/question`, questionDto);

  }
//user
getAllQuestion(id:number):Observable<any>{
  return this.http.get(`${this.apiUrl}/api/user/${id}`)
}
//admin
getAllQuestionAdmin(id:number):Observable<any>{
  return this.http.get(`${this.apiUrl}/api/admin/${id}`)
}
submitQuiz(data:any):Observable<any>{
  return this.http.post(`${this.apiUrl}/api/user/submittest`,data)
}
getQuizResult():Observable<any>{
  return this.http.get(`${this.apiUrl}/api/admin/test-result`)
}
// constructor(private http: HttpClient, private storageService: StorageService) {}

getResultById(): Observable<any> {
  const id = this.storageService.getUserId();
  return this.http.get(`${this.apiUrl}/api/user/test-result/${id}`);
}
// 
// 
deleteById(quizId: number): Observable<any> {
  return this.http.delete(`${this.apiUrl}/api/admin/delete/${quizId}`);
}
getQuizById(quizId:number):Observable<any> {
  return this.http.get(`${this.apiUrl}/api/admin/quiz/${quizId}`);
}
updateQuiz(id:number, quizData:any):Observable<any> {
  return this.http.put(`${this.apiUrl}/api/admin/quizzes/${id}`,quizData);
}

googleLogin(token: string): Observable<any> {
  return this.http.post(`${this.apiUrl}/auth/login`, { token });
}






//   // Fix the string interpolation here
//   // createQuiz(quiz: Quiz): Observable<any> {
//   //   createQuiz(quiz: Quiz): Observable<any> {

//   //   return this.http.post(`${this.apiUrl}/quizzes`, quiz);  // Use backticks for string interpolation
//   // }

//   getQuizzes(): Observable<any[]> {
//     return this.http.get<any[]>(`${this.apiUrl}/api/player/getquiz`);
//   }

//   getQuizById(quizId: number): Observable<any> {
//     return this.http.get<any>(`${this.apiUrl}/api/player/${quizId}/question`);
//   }
//   deleteById(quizId: number): Observable<any> {
//     return this.http.delete(`${this.apiUrl}/delete/${quizId}`);
//   }
//   // 🟢 Quiz Create karega
//   createQuiz(quiz: Quiz): Observable<Quiz> {
//     return this.http.post<Quiz>(`${this.apiUrl}/quizzes`, quiz);
//   }

//   addQuestion(quizId: number, question:  Question ): Observable<any> {
//     return this.http.post(`${this.apiUrl}/quizzes/{quizId}/questions`, question);
//   }


  

}