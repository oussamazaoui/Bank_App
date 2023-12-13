import {Component, OnInit} from '@angular/core';
import {AuthServiceService} from "../../../../services/auth-service.service";
import {Route, Router, RouterModule} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  isInputClickedCal = false;
  isDashboardInputClicked:boolean=false;
  isUserInputClicked:boolean=false;

  constructor(private auth:AuthServiceService) {
  }

  onLogOut() {
    this.auth.logout()
  }
  onInputClick() {
    this.isInputClickedCal=true;
    this.isUserInputClicked=false;
    this.isDashboardInputClicked=false;
  }

  onInputClickDashboard() {
    this.isDashboardInputClicked=true;
    this.isUserInputClicked=false;
    this.isInputClickedCal=false;
  }
  onInputClickUsers() {
    this.isUserInputClicked=true;
    this.isInputClickedCal=false;
    this.isDashboardInputClicked=false;

  }
}
