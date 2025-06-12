import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddQuestionQuizComponent } from './add-question-quiz.component';

describe('AddQuestionQuizComponent', () => {
  let component: AddQuestionQuizComponent;
  let fixture: ComponentFixture<AddQuestionQuizComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddQuestionQuizComponent]
    });
    fixture = TestBed.createComponent(AddQuestionQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
