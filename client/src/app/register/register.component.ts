import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit{
  errorMessages: { [key: string]: string } = {};

  constructor(private service: AuthService,private router:Router,private builder:FormBuilder,private toasters:ToastrService) {
  }

  public register_form !: FormGroup;

  ngOnInit() {
  this.register_form=this.builder.group({
    firstName:this.builder.control('',Validators.required),
    lastName:this.builder.control('',Validators.required),
    email:this.builder.control('',Validators.compose([Validators.required,Validators.email])),
    id_Card:this.builder.control('',Validators.compose([Validators.required,Validators.maxLength(8)])),
    password:this.builder.control('',Validators.compose([Validators.required,Validators.minLength(8)])),
    confirm_password:this.builder.control('',Validators.compose([Validators.required,Validators.minLength(8)]))
  });
  }

  onRegister() {
    console.log(this.register_form.controls);
    this.errorMessages = {}; // Reset error messages
    // Object.keys(this.register_form.controls).forEach(field => {
    //   const control = this.register_form.get(field);
    //   if (control && (control.value.trim() === '' || (control.touched && control.invalid))) {
    //     this.errorMessages[field] = `${field} is required`;
    //   }
    // });
    if (this.register_form.get('password')?.value?.trim() !== this.register_form.get('confirm_password')?.value?.trim() && (this.register_form.get('password')?.value?.length>0 &&  this.register_form.get('confirm_password')?.value?.length>0)) {
      this.errorMessages['equal'] = 'Passwords do not match';
    }
    if (this.register_form.valid) {
      this.service
        .register(this.register_form.value)
        .subscribe(
          (response) => {
            console.log(response);

            // this.toasters.success("Registered successfully ")
            this.router.navigate(['/login'],{ queryParams: { registrationSuccess: true } });
          },
          (error)=>{
            console.error(error);
            this.toasters.error("Registration failed");
          }
        );

    }
  }
  get FirstName():FormControl{
    return this.register_form.get('firstName') as FormControl;
  }
  get LastName():FormControl{
    return this.register_form.get('lastName') as FormControl;
  }
  get Email():FormControl{
    return this.register_form.get('email') as FormControl;
  }
  get Password():FormControl{
    return this.register_form.get('password') as FormControl;
  }
  get Confirm_Password():FormControl{
    return this.register_form.get('confirm_password') as FormControl;
  }
  get Id_Card():FormControl{
    return this.register_form.get('id_Card') as FormControl;
  }


}





