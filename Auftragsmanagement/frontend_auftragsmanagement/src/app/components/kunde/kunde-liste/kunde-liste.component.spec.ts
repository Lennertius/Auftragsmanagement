import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KundeListeComponent } from './kunde-liste.component';

describe('KundeListeComponent', () => {
  let component: KundeListeComponent;
  let fixture: ComponentFixture<KundeListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KundeListeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KundeListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
