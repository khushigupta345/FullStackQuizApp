import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMyResultComponent } from './view-my-result.component';

describe('ViewMyResultComponent', () => {
  let component: ViewMyResultComponent;
  let fixture: ComponentFixture<ViewMyResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewMyResultComponent]
    });
    fixture = TestBed.createComponent(ViewMyResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
