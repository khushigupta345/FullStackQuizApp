import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewResultQuizComponent } from './view-result-quiz.component';

describe('ViewResultQuizComponent', () => {
  let component: ViewResultQuizComponent;
  let fixture: ComponentFixture<ViewResultQuizComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewResultQuizComponent]
    });
    fixture = TestBed.createComponent(ViewResultQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
