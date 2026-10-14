import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CorpusModal } from './corpus-modal';

describe('CorpusModal', () => {
  let component: CorpusModal;
  let fixture: ComponentFixture<CorpusModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CorpusModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CorpusModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
