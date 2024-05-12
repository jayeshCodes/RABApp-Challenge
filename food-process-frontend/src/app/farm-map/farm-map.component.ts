import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, OnDestroy } from '@angular/core';
import { Map, MapStyle, Marker } from '@maptiler/sdk';
import { Config } from '@maptiler/sdk';
import '@angular/compiler';
import { FarmService } from '../farm.service';
import { Farm } from '../farm';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import '@maptiler/sdk/dist/maptiler-sdk.css';

@Component({
  selector: 'app-farm-map',
  standalone: true,
  templateUrl: './farm-map.component.html',
  styleUrl: './farm-map.component.css',
  exportAs: 'FarmMapComponent',
})
export class FarmMapComponent implements OnInit, AfterViewInit, OnDestroy {
  map: Map | undefined;
  apiKey = '2y11IsDWcbWsk7LeLPE6';
  public farms: Farm[] = [];
  public farm: Farm = {
    premiseid: '',
    total_animal: 0,
    farm_company: '',
    farm_longitude: 0.0,
    farm_latitude: 0.0,
  };

  constructor(private farmService: FarmService, private router: Router) {}

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  ngOnInit(): void {
    this.getFarms();
  }

  ngAfterViewInit() {
    if (this.farms.length === 0) {
      // If there are no farms yet, set default coordinates
      const defaultState = { lng: -78.6382, lat: 35.7796, zoom: 4.5 };
      this.initializeMap(defaultState);
    } else {
      // Calculate average coordinates
      const avgCoordinates = this.calculateAverageCoordinates();

      // Use the average coordinates to initialize the map
      const initialState = { lng: avgCoordinates.lng, lat: avgCoordinates.lat, zoom: 4.5 };
      this.initializeMap(initialState);
    }
  }

  private initializeMap(initialState: { lng: number, lat: number, zoom: number }) {
    this.map = new Map({
      container: this.mapContainer.nativeElement,
      style: MapStyle.STREETS,
      center: [initialState.lng, initialState.lat],
      zoom: initialState.zoom,
      apiKey: this.apiKey,
    });
  }

  public getFarms(): void {
    this.farmService.getFarms().subscribe(
      (response: Farm[]) => {
        this.farms = response;
        this.createMarkers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private createMarkers() {
    // Loop through the array of farms and create an array of points
    const points = this.farms.map((farm) => [farm.farm_longitude, farm.farm_latitude]);

    // Add markers for each point on the map
    points.forEach((point) => {
      new Marker({ color: '#FF0000' }).setLngLat([point[0], point[1]]).addTo(this.map!); // Use ! operator to assert non-null
    });
  }

  ngOnDestroy() {
    this.map?.remove();
  }

  navigateToFarmData() {
    this.router.navigate(['farm-data']);
  }

  private calculateAverageCoordinates() {
    const totalFarms = this.farms.length;
    let totalLongitude = 0;
    let totalLatitude = 0;

    // Calculate total longitude and latitude
    this.farms.forEach((farm) => {
      totalLongitude += farm.farm_longitude;
      totalLatitude += farm.farm_latitude;
    });

    // Calculate average longitude and latitude
    const avgLongitude = totalLongitude / totalFarms;
    const avgLatitude = totalLatitude / totalFarms;

    return { lng: avgLongitude, lat: avgLatitude };
  }
}
