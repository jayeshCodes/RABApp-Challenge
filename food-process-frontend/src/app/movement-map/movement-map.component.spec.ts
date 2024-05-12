import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovementMapComponent } from './movement-map.component';

describe('MovementMapComponent', () => {
  let component: MovementMapComponent;
  let fixture: ComponentFixture<MovementMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovementMapComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MovementMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
