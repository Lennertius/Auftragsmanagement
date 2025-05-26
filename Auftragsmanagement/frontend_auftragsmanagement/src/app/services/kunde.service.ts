import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Kunde, Kunde_ohne_ID } from '../interfaces/interfaces.model';

@Injectable({
  providedIn: 'root'
})
export class KundeService {

private ServerURL_Kunde = 'http://localhost:8080/kunde';

 private kundeErstelltSubject = new Subject<void>();

  constructor(private http: HttpClient) { }
 

    getKunde(): Observable<Kunde[]> {
      return this.http.get<Kunde[]>(this.ServerURL_Kunde);
    }
  
    getKundeById(id: number): Observable<Kunde> {
      return this.http.get<Kunde>(`${this.ServerURL_Kunde}/${id}`);
    }
  
    updateKunde(id: number, kunde: Kunde_ohne_ID): Observable<Kunde_ohne_ID> {
      return this.http.put<Kunde_ohne_ID>(`${this.ServerURL_Kunde}/${id}`, kunde);
    }

        KundelisteUpdate(): void {
        this.kundeErstelltSubject.next();
        }
        getKundeErstelltListener(): Observable<void> {
        return this.kundeErstelltSubject.asObservable();
        }
  
    createKunde(kunde: Kunde_ohne_ID): Observable<Kunde_ohne_ID> {
      return this.http.post<Kunde_ohne_ID>(this.ServerURL_Kunde, kunde);
    }

    deleteKundeById(id: number): Observable<void> {
      return this.http.delete<void>(`${this.ServerURL_Kunde}/${id}`);
    }

}
