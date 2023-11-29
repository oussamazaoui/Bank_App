import { Injectable } from '@angular/core';
import {Login, LoginResponse, Register} from "../modules/login";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  basedUrl:String="http://localhost:8080/api/auth/"
  constructor(private http:HttpClient) {
  }
  login(email:string,password:string):Observable<LoginResponse>{
    const loginUrl= `${this.basedUrl}login`;
    const credentials = {email, password};
    return this.http.post<LoginResponse>(loginUrl,credentials);
  }

  register(email:string,password:string,card_id:string,lastName:string,firstName:string){
    const registerUrl=`${this.basedUrl}register`;
    const info={email,password,card_id,lastName,firstName}
    return this.http.post<LoginResponse>(registerUrl,info);
  }
}
