import { Component, OnInit } from '@angular/core';
import { SearchService } from './search.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchedComponent implements OnInit {

  constructor(private route: ActivatedRoute, private searchService : SearchService) { }

  ngOnInit(): void  {
  }

}
