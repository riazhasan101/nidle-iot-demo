import { Component, OnInit } from '@angular/core'; import { HttpClient } from '@angular/common/http';
@Component({ selector: 'app-root', templateUrl: './app.component.html'})
export class AppComponent implements OnInit{
 devices:any[]=[]; constructor(private http:HttpClient){}
 ngOnInit(){ this.http.get<any[]>('http://localhost:8080/api/devices').subscribe(d=>this.devices=d); }
}
