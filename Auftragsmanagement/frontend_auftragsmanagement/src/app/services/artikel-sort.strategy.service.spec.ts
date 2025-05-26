import { TestBed } from '@angular/core/testing';

import { ArtikelSortStrategyService } from './artikel-sort.strategy.service';

describe('ArtikelSortStrategyService', () => {
  let service: ArtikelSortStrategyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArtikelSortStrategyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
