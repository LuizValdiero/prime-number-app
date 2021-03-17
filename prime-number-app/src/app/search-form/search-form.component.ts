import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';

import { PrimeNumbersService } from '../services/prime-numbers.service';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent implements OnInit {

  constructor(private service: PrimeNumbersService) { }

  valuesFormat: RegExp = /^\s*[-]?\d+\s+[-]?\d+\s*$/
  values: string | undefined;
  lastSearch: string = ''
  resultList: string = ''
  message: string = 'ok'

  info: boolean = false
  ngOnInit(): void {
  }

  onSubmit(f: NgForm) {
    f.value.info = ''

    if(f.valid) {
      const v = f.value.field.trim().replace(/\s+/g, ' ').split(' ')
      if (v && this.lastSearch !== f.value.field) {
        this.lastSearch = f.value.field
        this.service.getPrimeNumbers(v[0],v[1])
        .then( data => { this.resultList = data.toString(); console.log(this.resultList)})
        .catch(reason => { this.lastSearch = ''; console.log(reason); this.showError() })
      }
    }
  }
  showError() {
    this.resultList = 'Não foi possivel realizar a busca. Por favor tente mais tarde!'
  }
}
