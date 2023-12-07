import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminDashboardsComponent} from "./components/admin-dashboards/admin-dashboards.component";
import {CardsComponent} from "./components/cards/cards.component";

const routes: Routes = [
  {path:'',component:AdminDashboardsComponent,children:[
      {path:'dashboard',component:CardsComponent}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
