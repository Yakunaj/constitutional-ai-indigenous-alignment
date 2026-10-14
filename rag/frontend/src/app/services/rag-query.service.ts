import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

import { RAG_API_BASE_URL, RagQueryRequest, RagQueryResponse } from './rag-api.types';

@Injectable({ providedIn: 'root' })
export class RagQueryService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(RAG_API_BASE_URL);

  query(request: RagQueryRequest): Observable<RagQueryResponse> {
    return this.http.post<RagQueryResponse>(`${this.apiBaseUrl}/`, request);
  }
}
