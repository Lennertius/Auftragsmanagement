import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Artikel } from '../interfaces/interfaces.model';
import { Artikel_ohne_ID } from '../interfaces/interfaces.model';

@Injectable({
  providedIn: 'root'
})
export class ArtikelService {

  private ServerURL = 'http://localhost:8080/artikel';

  private artikelErstelltSubject = new Subject<void>();

  constructor(private http: HttpClient) {}

  getArtikel(): Observable<Artikel[]> {
    return this.http.get<Artikel[]>(this.ServerURL);
  }

  getArtikelById(id: number): Observable<Artikel> {
    return this.http.get<Artikel>(`${this.ServerURL}/${id}`);
  }

  updateArtikel(id: number, artikel: Artikel_ohne_ID): Observable<Artikel_ohne_ID> {
    return this.http.put<Artikel_ohne_ID>(`${this.ServerURL}/${id}`, artikel);
  }

  createArtikel(artikel: Artikel_ohne_ID): Observable<Artikel_ohne_ID> {
    return this.http.post<Artikel_ohne_ID>(this.ServerURL, artikel);
  }
      
        artikellisteUpdate(): void {
        this.artikelErstelltSubject.next();
        }
        getArtikelErstelltListener(): Observable<void> {
        return this.artikelErstelltSubject.asObservable();
        }
        
  deleteArtikelById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.ServerURL}/${id}`);
  }
}
