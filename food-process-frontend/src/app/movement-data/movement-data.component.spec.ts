import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovementDataComponent } from './movement-data.component';

describe('MovementDataComponent', () => {
  let component: MovementDataComponent;
  let fixture: ComponentFixture<MovementDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovementDataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MovementDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
