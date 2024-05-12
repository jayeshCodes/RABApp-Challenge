import {
  Component,
  OnInit,
  ViewChild,
  ElementRef,
  AfterViewInit,
  OnDestroy,
} from '@angular/core';
import { Map, MapStyle, Marker, PolylineLayerOptions, AddLayerObject } from '@maptiler/sdk';
import { MovementService } from '../movement.service';
import { Movement } from '../movement';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import '@maptiler/sdk/dist/maptiler-sdk.css';
import '@maptiler/sdk';

@Component({
  selector: 'app-movement-map',
  templateUrl: './movement-map.component.html',
  styleUrls: ['./movement-map.component.css'],
})
export class MovementMapComponent implements OnInit, AfterViewInit, OnDestroy {
  map: Map | undefined;
  apiKey = '2y11IsDWcbWsk7LeLPE6';
  movements: Movement[] = [];
  geoJSONObject: any;

  constructor(
    private movementService: MovementService,
    private router: Router,
  ) {}

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  ngOnInit(): void {
    this.getMovements();
  }

  ngAfterViewInit(): void {
    this.initializeMap();
  }

  ngOnDestroy(): void {
    this.map?.remove();
  }

  private getMovements(): void {
    this.movementService.getMovements().subscribe(
      (response: Movement[]) => {
        this.movements = response;
        this.geoJSONObject = this.convertMovementsToGeoJSON(this.movements);
        this.initializeMap(); // You may call this here if needed after obtaining the data
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private initializeMap(): void {
    if (!this.movements.length || !this.mapContainer) return;

    const initialState = {
      lng: -78.6382,
      lat: 35.7796,
      zoom: 4.5,
    };

    this.map = new Map({
      container: this.mapContainer.nativeElement,
      style: MapStyle.STREETS,
      center: [initialState.lng, initialState.lat],
      zoom: initialState.zoom,
      apiKey: this.apiKey,
    });

    const originPoints = this.movements.map((movement) => [
      movement.originLon,
      movement.originLat,
    ]);

    const destinationPoints = this.movements.map((movement) => [
      movement.destinationLon,
      movement.destinationLat,
    ]);

    // Add a line to the map
    this.map.on('load', () => {
      this.movements.forEach((movement, index) => {
        // Add a line to the map for each movement
        const routeCoordinates = [
          [movement.originLon, movement.originLat],
          [movement.destinationLon, movement.destinationLat],
        ];
        const routeSourceId = `route-${index}`;
    const routeLayerId = `route-${index}`;
      this.map?.addSource(routeSourceId, {
        'type': 'geojson',
        'data': {
          'type': 'Feature',
          'properties': {},
          'geometry': {
            'type': 'LineString',
            'coordinates': routeCoordinates,
            
          }
        }
      });

      this.map?.addLayer({
        'id': routeLayerId,
        'type': 'line',
        'source': routeSourceId,
        'layout': {
          'line-join': 'bevel',
          'line-cap': 'round',
        },
        'paint': {
          'line-color': '#888', // Alternate colors
          'line-width': 4,
              }
      });
    });})

    

    originPoints.forEach((point) => {
      new Marker({ color: '#00FF00' })
        .setLngLat([point[0], point[1]])
        .addTo(this.map!);

    });

    destinationPoints.forEach((point) => {
      new Marker({ color: '#FF0000' })
        .setLngLat([point[0], point[1]])
        .addTo(this.map!);
    });
  }

  public navigateToMovementData(): void {
    this.router.navigate(['movement-data']);
  }

  // traj = new GeoJSON().line(
  //   [
  //     [-5, 55],
  //     [2, 40]
  //   ],
  //   { id: 1, properties: { name: "Trajectory 1" } }
  // ).featureCollection

  public convertMovementsToGeoJSON(movements: Movement[]): any {
    const features = movements.map((movement) => ({
      type: 'Feature',
      properties: {},
      geometry: {
        coordinates: [
          [movement.originLon, movement.originLat],
          [movement.destinationLon, movement.destinationLat],
        ],
        type: 'LineString',
      },
    }));

    return {
      type: 'FeatureCollection',
      features: features,
    };
  }
}
