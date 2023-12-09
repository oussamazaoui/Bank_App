import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthServiceService} from "../services/auth-service.service";

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

  constructor(private service:AuthService, private route: ActivatedRoute, private builder:FormBuilder,private router:Router,private userhservice:AuthServiceService) {
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
        this.userhservice.setToken(response.token);
        this.userhservice.setId(response.id);
        if(response.id==1) {
          this.router.navigate(['admin']);
        }
        else
          this.router.navigate(['user']);
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
