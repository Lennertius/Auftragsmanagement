import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auftrag, Auftrag_mit_Artikel, Auftrag_mit_Artikel_ohne_id } from '../interfaces/interfaces.model'; 

@Injectable({ providedIn: 'root' })
export class AuftragService {
  private baseUrl = 'http://localhost:8080/auftrag';

  constructor(private http: HttpClient) {}

  getAuftraege(): Observable<Auftrag[]> {
    return this.http.get<Auftrag_mit_Artikel[]>(this.baseUrl);
  }

  getAuftraegeById(id: number): Observable<Auftrag_mit_Artikel> {
    return this.http.get<Auftrag_mit_Artikel>(`${this.baseUrl}/${id}`);
  }

  createAuftrag(Auftrag: Auftrag_mit_Artikel_ohne_id) {
    return this.http.post<Auftrag>(this.baseUrl, Auftrag);
  }

  deleteAuftrag(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

