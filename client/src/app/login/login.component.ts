import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {LoginResponse} from "../modules/login";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{
  email:string='';
  password:string='';
  token:string='';
  id:number=0;
  registrationSuccess: boolean = false;

  constructor(private service:AuthService,private route: ActivatedRoute) {
 }
 ngOnInit() {
   this.route.queryParams.subscribe((params) => {
     this.registrationSuccess = params['registrationSuccess'] === 'true';
 })
 }

  onLoging() {
    console.log(this.email,this.password);

    this.service.login(this.email,this.password).subscribe(
      (response)=>{
        console.log(response);
        this.token=response.token;
        this.id=response.id;
        },
      (error)=>{console.error('Login error', error);}
    )
  }
}
