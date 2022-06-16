import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElearningLogoComponent } from './elearning-logo.component';

describe('SwimbaLogoComponent', () => {
  let component: ElearningLogoComponent;
  let fixture: ComponentFixture<ElearningLogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElearningLogoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElearningLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
