import { Component, OnInit, ElementRef } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  constructor() { }

  ngAfterViewInit() {
  }
  ngOnInit() {
    //System.import('script.js'); //or below one
    //require()
    require('script.js');
  }

}
