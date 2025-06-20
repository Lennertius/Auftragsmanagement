import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuftragComponent } from './auftrag-formular.component';

describe('AuftragComponent', () => {
  let component: AuftragComponent;
  let fixture: ComponentFixture<AuftragComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuftragComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuftragComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
