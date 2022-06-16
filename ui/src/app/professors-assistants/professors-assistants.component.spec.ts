import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorsAssistantsComponent } from './professors-assistants.component';

describe('ProfessorsAssistantsComponent', () => {
  let component: ProfessorsAssistantsComponent;
  let fixture: ComponentFixture<ProfessorsAssistantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorsAssistantsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorsAssistantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
