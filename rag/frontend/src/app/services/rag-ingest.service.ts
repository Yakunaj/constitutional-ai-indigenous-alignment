import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

import { RAG_API_BASE_URL, RagIngestMetadata, RagIngestResponse } from './rag-api.types';

@Injectable({ providedIn: 'root' })
export class RagIngestService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(RAG_API_BASE_URL);

  ingestPdf(file: File, metadata: RagIngestMetadata): Observable<RagIngestResponse> {
    const formData = new FormData();
    formData.append('archivo', file, file.name);
    formData.append('metadata', new Blob([JSON.stringify(metadata)], { type: 'application/json' }));

    return this.http.post<RagIngestResponse>(`${this.apiBaseUrl}/ingest`, formData);
  }
}