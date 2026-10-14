import { InjectionToken } from '@angular/core';

export const RAG_API_BASE_URL = new InjectionToken<string>('RAG_API_BASE_URL');

export interface RagIngestMetadata {
  titulo: string;
  descripcion?: string;
}

export interface RagIngestResponse {
  documentoId: number;
  titulo: string;
  nombreArchivoOriginal: string;
  rutaAlmacenamiento: string;
  estado: 'PROCESANDO' | 'COMPLETADO' | 'ERROR';
  fragmentosProcesados: number;
  fechaIngesta: string;
}

export interface RagQueryRequest {
  question: string;
  limit?: number;
  minSimilarity?: number;
  language?: string;
}

export interface RagRetrievedChunk {
  fragmentId: number;
  documentoId: number;
  tituloDocumento: string;
  nombreArchivoOriginal: string;
  ordenFragmento: number;
  text: string;
  similarity: number;
}

export interface RagQueryResponse {
  question: string;
  answer: string;
  retrievedIds: number[];
  similarities: number[];
  retrievedChunks: RagRetrievedChunk[];
  latencyMs: number;
  model: string;
}
