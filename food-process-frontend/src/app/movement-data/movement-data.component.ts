import { Component } from '@angular/core';
import { Movement } from '../movement';
import { MovementService } from '../movement.service';
import { Farm } from '../farm';
import { FarmService } from '../farm.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { response } from 'express';
import { Router } from '@angular/router';
import { error } from 'console';
import { CompanyService } from '../company.service';
import { Company } from '../company';

@Component({
  selector: 'app-movement-data',
  standalone: true,
  imports: [NgFor, FormsModule, NgIf],
  templateUrl: './movement-data.component.html',
  styleUrl: './movement-data.component.css',
})
export class MovementDataComponent {
  public movements: Movement[] = [];
  public movement: Movement = {
    id: 0,
    accountCompany: '',
    newMovementReason: '',
    newSpecies: '',
    newOriginAddress: '',
    newOriginCity: '',
    newOriginName: '',
    newOriginPostalCode: '',
    newOriginPremId: '',
    newOriginState: '',
    newDestinationAddress: '',
    newDestinationCity: '',
    newDestinationName: '',
    newDestinationPostalCode: '',
    newDestinationPremId: '',
    newDestinationState: '',
    originLat: 0,
    originLon: 0,
    destinationLat: 0,
    destinationLon: 0,
    newNumItemsMoved: 0,
    newShipmentsStartDate: '',
  };
  public showDeleteIcons: boolean = false; // Array to keep track of delete icons
  public showUpdateIcons: boolean = false;
  public addRowClicked: boolean = false;
  public farms: Farm[] = [];
  public validIds: String[] = [];
  public deleteClicked: boolean=false;
  public editRowId: number | null = null;
  public company: Company[] = [];
  public companyNames: string[] = [];


  constructor(
    private movementService: MovementService,
    private router: Router,
    private farmService: FarmService,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    this.getMovements();
    this.getFarms();
    this.getValidIds();
    this.getValidCompanyNames();
  }

  public getMovements(): void {
    this.movementService.getMovements().subscribe(
      (response: Movement[]) => {
        this.movements = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    console.log(this.movements);
  }

  public onAddFarm(addForm: NgForm): void {}

  public getFarms(): void {
    this.farmService.getFarms().subscribe(
      (response: Farm[]) => {
        this.farms = response;
        if (this.farms) {
          console.log("records exist", this.farms);
          this.getValidIds();
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  public getValidIds(): void {
    console.log(this.farms);
    for (const farm of this.farms) {
      this.validIds.push(farm.premiseid);
    }
  }

  public getValidCompanyNames(): void {
    this.companyService.getCompanies().subscribe(
      (response: Company[]) => {
        this.companyNames = response.map(company => company.companyName);
      },
      (error: HttpErrorResponse) => {
        console.error('Failed to fetch valid company names:', error);
      }
    );
  }

  public addRow(): void {
    this.addRowClicked = !this.addRowClicked;
  }

  public deleteClick():void {
    this.deleteClicked=!this.deleteClicked;
  }

  public checkValidations(): boolean {
    // Check if any field is empty
    if (
      !this.movement.accountCompany ||
      !this.movement.newMovementReason ||
      !this.movement.newSpecies ||
      !this.movement.newOriginPremId ||
      !this.movement.newDestinationPremId ||
      !this.movement.newShipmentsStartDate
    ) {
      alert('Please fill in all fields.');
      return false;
    }
  
    // Check if number of units is greater than 0
    if (this.movement.newNumItemsMoved <= 0) {
      alert('Number of units must be greater than 0.');
      return false;
    }
  
    // Check if date is entered in YYYY-MM-DD format
    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
    if (!dateRegex.test(this.movement.newShipmentsStartDate)) {
      alert('Date must be entered in YYYY-MM-DD format.');
      return false;
    }
  
    // Capitalize the reason
    this.movement.newMovementReason = this.movement.newMovementReason.toUpperCase();
  
    // All validations passed
    return true;
  }


  public onSubmit(movement: Movement): void {
    if (movement! && this.checkValidations()) {
      this.movementService.addMovement(this.movement).subscribe(
        (response: Movement) => {
          console.log('Movement added successfully:', response);
          this.addRowClicked = false;
          this.getMovements();
        },
        (error: HttpErrorResponse) => {
          console.error('Failed to add movement:', error);
        }
      );
    }
  }

  public navigateToMovementMap() : void{
    this.router.navigate(['movement-map']);
  }

  formatDate(unixTimestamp: string): string {
    const date = new Date(unixTimestamp);
    return `${date.getFullYear()}-${(date.getMonth() + 1)
      .toString()
      .padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
  }

  public deleteRow(x:number):void{
    if(this.deleteClicked){
      this.movementService.deleteMovement(x).subscribe(
        (response:void)=>{
          this.getMovements();
        },(error:HttpErrorResponse)=>{
          console.error("Failed to delete", error.message);
          this.getMovements();
        }
      );
    }
  }
}
