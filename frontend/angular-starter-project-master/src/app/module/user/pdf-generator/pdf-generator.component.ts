import { Component, OnInit } from '@angular/core';
import jsPDF from 'jspdf';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-pdf-generator',
  templateUrl: './pdf-generator.component.html',
  styleUrls: ['./pdf-generator.component.css']
})
export class PdfGeneratorComponent implements OnInit {
  resultData: any[] = [];

  constructor(private quizService: QuizService) {}

  ngOnInit(): void {
    this.fetchResults();
  }

  fetchResults(): void {
    this.quizService.getResultById().subscribe(res => {
      this.resultData = res;
      console.log("Result data fetched for PDF:", this.resultData);
    });
  }

  downloadPdfForAllResults(): void {
    const doc = new jsPDF();
    let y = 10;

    this.resultData.forEach((quiz: any, index: number) => {
      doc.setFontSize(16);
      doc.text(`Quiz ${index + 1} (Quiz ID: ${quiz.quizId})`, 10, y);
      y += 8;

      doc.setFontSize(12);
      doc.text(`Total Questions: ${quiz.totalQuestion}`, 10, y);
      y += 6;
      doc.text(`Correct Answers: ${quiz.correctAnswers}`, 10, y);
      y += 6;
      doc.text(`Percentage: ${quiz.percentage}%`, 10, y);
      y += 6;
      doc.text(`Result: ${quiz.percentage >= 40 ? 'PASS' : 'FAIL'}`, 10, y);
      y += 10;

      doc.setFontSize(11);
      quiz.questionAnswers.forEach((qa: any, i: number) => {
        doc.text(`${i + 1}. ${qa.questionText}`, 10, y); y += 5;
        doc.text(` Correct: ${qa.correctOption}`, 12, y); y += 5;
        doc.text(` Selected: ${qa.userAnswer}`, 12, y);
        y += 7;

        if (y > 270) {
          doc.addPage();
          y = 10;
        }
      });

      y += 10;
      if (y > 270) {
        doc.addPage();
        y = 10;
      }
    });

    doc.save('All-Quiz-Results.pdf');
  }
}