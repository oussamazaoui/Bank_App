import { Component } from '@angular/core';
import {AuthServiceService} from "../../../../services/auth-service.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {

  constructor(private auth:AuthServiceService) {
  }
  onLogOut() {
    this.auth.logout()
  }
}
