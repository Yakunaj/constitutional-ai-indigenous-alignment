import { AfterViewChecked, ChangeDetectorRef, Component, ElementRef, ViewChild, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { finalize } from 'rxjs';

import { RAG_API_BASE_URL, RagQueryResponse, RagRetrievedChunk } from '../../services/rag-api.types';
import { RagQueryService } from '../../services/rag-query.service';

interface Message {
  id: number;
  sender: 'user' | 'bot';
  text: string;
  isTyping?: boolean;
  documents?: RetrievedDocument[];
  showDocuments?: boolean;
  latencyMs?: number;
  model?: string;
}

interface RetrievedDocument {
  documentoId: number;
  tituloDocumento: string;
  nombreArchivoOriginal: string;
  previewText: string;
  fragments: RagRetrievedChunk[];
}

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './chat.html',
  styleUrls: ['./chat.css']
})
export class Chat implements AfterViewChecked {
  private readonly queryService = inject(RagQueryService);
  private readonly apiBaseUrl = inject(RAG_API_BASE_URL);
  private readonly cdr = inject(ChangeDetectorRef);

  @ViewChild('chatHistoryContainer') private chatHistory!: ElementRef;

  isMayaSelected = false;
  isRecording = false;
  isQueryLoading = false;
  currentPrompt = '';
  messages: Message[] = [];
  isFragmentsModalOpen = false;
  activeDocument: RetrievedDocument | null = null;
  private nextMessageId = 1;

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  onKeyDown(event: any) {
    if (this.isQueryLoading) {
      event.preventDefault();
      return;
    }

    if (!event.shiftKey) {
      event.preventDefault();
      this.sendQuery();
    }
  }

  sendQuery() {
    const text = this.currentPrompt.trim();
    if (!text || this.isQueryLoading) return;

    this.messages.push({ id: this.createMessageId(), sender: 'user', text });
    this.currentPrompt = '';
    this.isQueryLoading = true;

    const typingMessageId = this.createMessageId();
    const typingIndicatorIndex = this.messages.push({
      id: typingMessageId,
      sender: 'bot',
      text: '',
      isTyping: true,
    }) - 1;

    this.queryService
      .query({
        question: text,
        limit: 5,
        minSimilarity: 0.2,
        language: this.isMayaSelected ? 'yua' : 'es',
      })
      .pipe(finalize(() => {
        this.isQueryLoading = false;
        this.cdr.detectChanges();
      }))
      .subscribe({
        next: (response: RagQueryResponse) => {
          this.messages[typingIndicatorIndex] = this.buildBotMessage(response);
          this.cdr.detectChanges();
        },
        error: () => {
          this.messages[typingIndicatorIndex] = {
            id: typingMessageId,
            sender: 'bot',
            text: 'No fue posible consultar el backend RAG. Intenta de nuevo en unos segundos.',
          };
          this.cdr.detectChanges();
        },
      });
  }

  toggleMic() {
    this.isRecording = !this.isRecording;
  }

  openFragmentsModal(document: RetrievedDocument) {
    this.activeDocument = document;
    this.isFragmentsModalOpen = true;
    this.cdr.detectChanges();
  }

  getDocumentPdfUrl(documentId: number): string {
    return `${this.apiBaseUrl}/documentos/${documentId}/pdf`;
  }

  toggleRetrievedDocuments(messageId: number) {
    const message = this.messages.find((item) => item.id === messageId);

    if (!message?.documents?.length) {
      return;
    }

    message.showDocuments = !message.showDocuments;
    this.cdr.detectChanges();
  }

  closeFragmentsModal() {
    this.isFragmentsModalOpen = false;
    this.activeDocument = null;
    this.cdr.detectChanges();
  }

  closeFragmentsModalOnOverlay(event: MouseEvent) {
    if ((event.target as HTMLElement).classList.contains('modal-overlay')) {
      this.closeFragmentsModal();
    }
  }

  trackByMessageId(_: number, message: Message): number {
    return message.id;
  }

  private scrollToBottom(): void {
    if (this.chatHistory) {
      try {
        this.chatHistory.nativeElement.scrollTop = this.chatHistory.nativeElement.scrollHeight;
      } catch (err) {}
    }
  }

  private buildBotMessage(response: RagQueryResponse): Message {
    const documents = this.groupRetrievedChunks(response.retrievedChunks ?? []);

    return {
      id: this.createMessageId(),
      sender: 'bot',
      text: response.answer,
      documents,
      showDocuments: false,
      latencyMs: response.latencyMs,
      model: response.model,
    };
  }

  private groupRetrievedChunks(chunks: RagRetrievedChunk[]): RetrievedDocument[] {
    const groupedDocuments = new Map<number, RetrievedDocument>();

    for (const chunk of chunks) {
      const currentDocument = groupedDocuments.get(chunk.documentoId);

      if (currentDocument) {
        currentDocument.fragments.push(chunk);
        continue;
      }

      groupedDocuments.set(chunk.documentoId, {
        documentoId: chunk.documentoId,
        tituloDocumento: chunk.tituloDocumento,
        nombreArchivoOriginal: chunk.nombreArchivoOriginal,
        previewText: this.getPreviewText(chunk.text),
        fragments: [chunk],
      });
    }

    return Array.from(groupedDocuments.values())
      .map((document) => ({
        ...document,
        fragments: [...document.fragments].sort((left, right) => left.ordenFragmento - right.ordenFragmento),
      }))
      .sort((left, right) => left.fragments[0].ordenFragmento - right.fragments[0].ordenFragmento);
  }

  private getPreviewText(text: string, maxLength = 180): string {
    const compactText = text.replace(/\s+/g, ' ').trim();

    if (compactText.length <= maxLength) {
      return compactText;
    }

    return `${compactText.slice(0, maxLength).trimEnd()}...`;
  }

  private createMessageId(): number {
    return this.nextMessageId++;
  }
}