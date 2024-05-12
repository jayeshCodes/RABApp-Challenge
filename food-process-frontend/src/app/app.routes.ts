import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { FarmDataComponent } from './farm-data/farm-data.component';
import { MovementDataComponent } from './movement-data/movement-data.component';
import { FarmMapComponent } from './farm-map/farm-map.component';
import { MovementMapComponent } from './movement-map/movement-map.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    {path: '', redirectTo:'login', pathMatch:'full'},
    {path: 'login', component: LoginComponent},
    {path: 'signup', component: SignupComponent},
    {path: 'farm-map', component:FarmMapComponent},
    {path: 'farm-data', component:FarmDataComponent},
    {path: 'movement-data', component:MovementDataComponent},
    {path: 'farm-map', component:FarmMapComponent},
    {path: 'movement-map', component:MovementMapComponent}
];
