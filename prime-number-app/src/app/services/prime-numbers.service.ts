import { Injectable } from '@angular/core'
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http'
import { Observable, throwError } from 'rxjs'
import { retry, catchError } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class PrimeNumbersService {

  url: string = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Max-Age': '86400'})
  }

  getPrimeNumbers(value1: string, value2: string): Promise<Number[]> {
    return this.httpClient.get<Number[]>(
      this.url +
      '?value1=' + value1 +
      '&value2=' + value2
      ).pipe(retry(2))
      .toPromise()
  }
}
