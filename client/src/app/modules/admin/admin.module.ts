import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardsComponent } from './components/admin-dashboards/admin-dashboards.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { CardsComponent } from './components/cards/cards.component';
import { RecentUsersComponent } from './components/recent-transactions/recent-users.component';
import { OverviewComponent } from './components/overview/overview.component';


@NgModule({
  declarations: [
    AdminDashboardsComponent,
    SidebarComponent,
    CardsComponent,
    RecentUsersComponent,
    OverviewComponent
  ],
  exports: [
    SidebarComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
