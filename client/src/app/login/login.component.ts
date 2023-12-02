import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {LoginResponse} from "../modules/login";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{
  login_form !: FormGroup;
  token:string='';
  id:number=0;
  registrationSuccess: boolean = false;

  constructor(private service:AuthService, private route: ActivatedRoute, private builder:FormBuilder) {
 }
 ngOnInit() {
   this.route.queryParams.subscribe((params) => {
     this.registrationSuccess = params['registrationSuccess'] === 'true';
 })
  this.login_form=this.builder.group({
    email:this.builder.control('',Validators.compose([Validators.required,Validators.email])),
    password:this.builder.control('',Validators.required)
  });
 }

  onLoging() {
    console.log(this.login_form.value);
    this.service.login(this.login_form.value).subscribe(
      (response)=>{
        console.log(response);
        this.token=response.token;
        this.id=response.id;
        },
      (error)=>{console.error('Login error', error);}
    )
  }
  get Password():FormControl{
    return this.login_form.get('password') as FormControl;
  }
  get Email():FormControl{
    return this.login_form.get("email") as FormControl;
  }
}
