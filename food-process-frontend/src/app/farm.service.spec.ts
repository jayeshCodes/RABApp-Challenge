import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FarmService } from './farm.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Farm } from './farm';

describe("Hello", ()=>{
  it("should say hello", expect().nothing);
})

describe('FarmService', () => {
  let service: FarmService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FarmService]
    });
    service = TestBed.inject(FarmService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve farms from the API via GET', () => {
    const mockFarms: Farm[] = [
      { premiseid: '1', total_animal: 100, farm_company: 'Company 1', farm_latitude: 10.123, farm_longitude: 20.456 },
      { premiseid: '2', total_animal: 150, farm_company: 'Company 2', farm_latitude: 15.678, farm_longitude: 25.789 }
    ];

    service.getFarms().subscribe(farms => {
      expect(farms).toBeDefined();
    });

    const req = httpTestingController.expectOne(`${environment.apiBaseUrl}/farms/all`);
    expect(req.request.method).toEqual('GET');

    req.flush(mockFarms);
  });

  it('should add a farm via POST', () => {
    const newFarm: Farm = { premiseid: '3', total_animal: 200, farm_company: 'Company 3', farm_latitude: 30.456, farm_longitude: 40.789 };

    service.addFarm(newFarm).subscribe(farm => {
      expect(farm).toEqual(newFarm);
    });

    const req = httpTestingController.expectOne(`${environment.apiBaseUrl}/farms/add`);
    expect(req.request.method).toEqual('POST');

    req.flush(newFarm);
  });

  it('should update a farm via PUT', () => {
    const updatedFarm: Farm = { premiseid: '1', total_animal: 120, farm_company: 'Updated Company', farm_latitude: 11.123, farm_longitude: 21.456 };

    service.updateFarms(updatedFarm).subscribe(farm => {
      expect(farm).toEqual(updatedFarm);
    });

    const req = httpTestingController.expectOne(`${environment.apiBaseUrl}/farms/update`);
    expect(req.request.method).toEqual('PUT');

    req.flush(updatedFarm);
  });

  it('should delete a farm via DELETE', () => {
    const premiseId = '123';

    service.deleteFarms(premiseId).subscribe(() => {
      expect().nothing(); // nothing to expect in response for DELETE request
    });

    const req = httpTestingController.expectOne(`${environment.apiBaseUrl}/farms/delete/${premiseId}`);
    expect(req.request.method).toEqual('DELETE');

    req.flush({});
  });
});
