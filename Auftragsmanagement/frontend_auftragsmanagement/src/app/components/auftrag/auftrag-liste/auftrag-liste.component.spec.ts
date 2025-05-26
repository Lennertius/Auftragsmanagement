import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuftragListeComponent } from './auftrag-liste.component';

describe('AuftragListeComponent', () => {
  let component: AuftragListeComponent;
  let fixture: ComponentFixture<AuftragListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuftragListeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuftragListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
