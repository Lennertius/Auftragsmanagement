import { TestBed } from '@angular/core/testing';

import { ArtikelListMediatorService } from './artikel-list-mediator.service';

describe('ArtikelListMediatorService', () => {
  let service: ArtikelListMediatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArtikelListMediatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
