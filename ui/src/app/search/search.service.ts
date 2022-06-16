import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";


@Injectable({
    providedIn:'root'
})

export class SearchService{
    constructor(private http : HttpClient){
    }

    // public getSearchedItemsFromShop(query : string):Observable<Item[]>{
    //        return this.http.get<Item[]>('https://localhost:44392/api/Item/search/' + query); 
    // }
}

