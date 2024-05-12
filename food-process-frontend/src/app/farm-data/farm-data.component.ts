import { Component } from '@angular/core';
import { FarmService } from '../farm.service';
import { Farm } from '../farm';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { response } from 'express';
import { FarmMapComponent } from '../farm-map/farm-map.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-farm-data',
  standalone: true,
  imports: [NgFor, FormsModule, NgIf],
  templateUrl: './farm-data.component.html',
  styleUrl: './farm-data.component.css',
})
export class FarmDataComponent {
  public farms: Farm[] = [];
  public farm: Farm = { premiseid: '', total_animal: 0, farm_company:'', farm_longitude:0.0, farm_latitude:0.0 };
  public showDeleteIcons: boolean = false; // Array to keep track of delete icons
  public showUpdateIcons: boolean = false;

  constructor(private farmService: FarmService, private router:Router) {}

  ngOnInit(): void {
    this.getFarms();
  }

  public getFarms(): void {
    this.farmService.getFarms().subscribe(
      (response: Farm[]) => {
        this.farms = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddFarm(addForm: NgForm): void {
    document.getElementById('close-addFarmModal')?.click();
    this.farm.farm_company = addForm.value.farmCompany;
    this.farm.premiseid = addForm.value.farmId;
    this.farm.total_animal = addForm.value.numberOfUnits;
    this.farmService.addFarm(this.farm).subscribe(
      (response: Farm) => {
        this.getFarms();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    this.farm = { premiseid: '', total_animal: 0, farm_company:'', farm_latitude:0.0, farm_longitude:0.0 };
  }

  deleteFarm(premiseId: string): void {
    this.farmService.deleteFarms(premiseId).subscribe(
      () => {
        this.getFarms();
      },
      (error: HttpErrorResponse) => {
        this.getFarms();
      }
    );
  }

  updateFarm(farm : Farm): void{
    this.farmService.updateFarms(farm).subscribe(
      (response: Farm) => {
        this.getFarms();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  navigateToFarmMap(){
    this.router.navigate(['farm-map'])
  }

  toggleDeleteIcons(): void {
    this.showDeleteIcons = !this.showDeleteIcons;
  }


  toggleUpdateIcons(): void{
    this.showUpdateIcons = !this.showUpdateIcons;
  }
}
