import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {RequestParameter} from "@angular/cli/src/analytics/analytics-parameters";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  id_card: string = '';
  password: string = '';
  confirm_password: string = '';
  errorMessages: { [key: string]: string } = {};

  constructor(private service: AuthService,private router:Router) {
  }

  onRegister() {
    this.errorMessages = {}; // Reset error messages

    if (!this.firstName.trim()) {
      this.errorMessages['firstName'] = 'First Name is required';
    }

    if (!this.lastName.trim()) {
      this.errorMessages['lastName'] = 'Last Name is required';
    }

    if (!this.email.trim()) {
      this.errorMessages['email'] = 'Email is required';
    }

    if (!this.id_card.trim()) {
      this.errorMessages['id_card'] = 'ID Card is required';
    }

    if (!this.password.trim()) {
      this.errorMessages['password'] = 'Password is required';
    }

    if (!this.confirm_password.trim()) {
      this.errorMessages['confirm_password'] = 'Confirm Password is required';
    }

    if (this.password !== this.confirm_password) {
      this.errorMessages['equal'] = 'Passwords do not match';
    }
    if (Object.keys(this.errorMessages).length === 0) {
      this.service
        .register(this.email, this.password, this.id_card, this.lastName, this.firstName)
        .subscribe(
          (response) => {
            console.log(response);
            this.router.navigate(['/login'],{ queryParams: { registrationSuccess: true } });
          },
          (error) => {
            console.log(error);
          }
        );

    }


  }
}





