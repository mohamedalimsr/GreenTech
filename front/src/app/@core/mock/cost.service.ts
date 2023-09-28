import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cost } from '../data/cost';

@Injectable({
  providedIn: 'root'
})
export class CostService {

  private baseUrlp = 'http://localhost:8080/api/commands/costs';
  private baseUrlg = 'http://localhost:8080/api/queries/costs';

  constructor(private http: HttpClient) { }

  // Fetch a list of cost items
  getCostsList(): Observable<Cost[]> {
    return this.http.get<Cost[]>(`${this.baseUrlg}`);
  }

  // Fetch details for a specific cost item by ID
  getCostById(id: number): Observable<Cost> {
    return this.http.get<Cost>(`${this.baseUrlg}/${id}`);
  }

  // Create a new cost item
  createCost(cost: Cost): Observable<Object> {
    return this.http.post<Cost>(`${this.baseUrlp}`, cost );
    
    
  }

  // Update an existing cost item by ID
  updateCost(id: number, cost: Cost): Observable<Object> {
    return this.http.put<Cost>(`${this.baseUrlp}/${id}`, cost);
  }

  // Delete a cost item by ID
  deleteCost(id: number): Observable<Object> {
    return this.http.delete(`${this.baseUrlp}/${id}`);
  }
}
