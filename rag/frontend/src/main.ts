import 'zone.js';
import { bootstrapApplication } from '@angular/platform-browser';

import { appConfig } from './app/app.config';
import { App } from './app/app';
import { RAG_API_BASE_URL } from './app/services/rag-api.types';

interface RuntimeConfig {
  backendBaseUrl?: string;
}

const DEFAULT_BACKEND_BASE_URL = 'http://localhost:8080';

function normalizeBaseUrl(url: string): string {
  return url.replace(/\/+$/, '');
}

async function loadRuntimeConfig(): Promise<RuntimeConfig> {
  const response = await fetch('/config.json', { cache: 'no-store' });

  if (!response.ok) {
    throw new Error(`No se pudo cargar config.json (HTTP ${response.status})`);
  }

  return (await response.json()) as RuntimeConfig;
}

async function main(): Promise<void> {
  const runtimeConfig = await loadRuntimeConfig();
  const backendBaseUrl = normalizeBaseUrl(runtimeConfig.backendBaseUrl ?? DEFAULT_BACKEND_BASE_URL);
  const ragApiBaseUrl = `${backendBaseUrl}/api/chat`;

  await bootstrapApplication(App, {
    ...appConfig,
    providers: [
      ...(appConfig.providers ?? []),
      { provide: RAG_API_BASE_URL, useValue: ragApiBaseUrl }
    ]
  });
}

main().catch((err) => console.error(err));
