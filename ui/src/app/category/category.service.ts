import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { CategoryModel } from "./category.model";


@Injectable({
    providedIn:'root'
})


export class CategoryService{
    constructor(private http : HttpClient){
    }

    public getCategoriesFromShop():Observable<CategoryModel[]>{
           return this.http.get<CategoryModel[]>('https://localhost:44392/api/Category'); 
    }

}
