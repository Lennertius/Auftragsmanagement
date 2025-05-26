import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TEst2Component } from './test2.component';

describe('TEst2Component', () => {
  let component: TEst2Component;
  let fixture: ComponentFixture<TEst2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TEst2Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TEst2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
