import { ChangeDetectorRef, Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { finalize } from 'rxjs';

import { RagIngestService } from '../../services/rag-ingest.service';

@Component({
  selector: 'app-corpus-modal',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './corpus-modal.html',
  styleUrls: ['./corpus-modal.css']
})
export class CorpusModal {
  private readonly ingestService = inject(RagIngestService);
  private readonly cdr = inject(ChangeDetectorRef);

  @Input() isOpen = false;
  @Output() closeExited = new EventEmitter<void>();

  title = '';
  description = '';
  isDragOver = false;
  selectedFile: File | null = null;
  isUploading = false;
  errorMessage = '';
  successMessage = '';

  close() {
    if (this.isUploading) {
      return;
    }

    this.title = '';
    this.description = '';
    this.selectedFile = null;
    this.isDragOver = false;
    this.isUploading = false;
    this.errorMessage = '';
    this.successMessage = '';
    this.cdr.detectChanges();
    this.closeExited.emit();
  }

  closeOnOverlay(event: MouseEvent) {
    if (this.isUploading) {
      return;
    }

    if ((event.target as HTMLElement).classList.contains('modal-overlay')) {
      this.close();
    }
  }

  onDragOver(event: DragEvent) {
    if (this.selectedFile) {
      event.preventDefault();
      return;
    }

    event.preventDefault();
    this.isDragOver = true;
  }

  onDragLeave() {
    this.isDragOver = false;
  }

  onDrop(event: DragEvent) {
    event.preventDefault();

    if (this.selectedFile) {
      this.isDragOver = false;
      return;
    }

    this.isDragOver = false;
    if (event.dataTransfer?.files) {
      this.pickSinglePdf(event.dataTransfer.files[0] ?? null);
    }
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (this.selectedFile) {
      input.value = '';
      return;
    }

    this.pickSinglePdf(input.files?.[0] ?? null);
  }

  removeSelectedFile(event: MouseEvent) {
    event.preventDefault();
    event.stopPropagation();

    if (this.isUploading) {
      return;
    }

    this.selectedFile = null;
    this.errorMessage = '';
    this.successMessage = '';
    this.isDragOver = false;
    this.cdr.detectChanges();
  }

  onSubmit() {
    if (this.isUploading) {
      return;
    }

    const title = this.title.trim();

    if (!title) {
      this.errorMessage = 'El título es obligatorio.';
      this.successMessage = '';
      return;
    }

    if (!this.selectedFile) {
      this.errorMessage = 'Selecciona un PDF antes de guardar en el corpus.';
      this.successMessage = '';
      return;
    }

    this.isUploading = true;
    this.errorMessage = '';
    this.successMessage = '';
    this.cdr.detectChanges();

    this.ingestService
      .ingestPdf(this.selectedFile, {
        titulo: title,
        descripcion: this.description.trim() || undefined,
      })
      .pipe(
        finalize(() => {
          this.isUploading = false;
          this.cdr.detectChanges();
        })
      )
      .subscribe({
        next: (response) => {
          this.successMessage = `Documento ${response.documentoId} cargado con ${response.fragmentosProcesados} fragmentos.`;
          this.title = '';
          this.description = '';
          this.selectedFile = null;
          this.cdr.detectChanges();
        },
        error: () => {
          this.errorMessage = 'No fue posible ingerir el documento.';
          this.cdr.detectChanges();
        },
      });
  }

  get fileLabel(): string {
    return this.selectedFile ? `${this.selectedFile.name} (${(this.selectedFile.size / 1024).toFixed(1)} KB)` : '';
  }

  private pickSinglePdf(file: File | null): void {
    this.errorMessage = '';
    this.successMessage = '';

    if (!file) {
      this.selectedFile = null;
      this.cdr.detectChanges();
      return;
    }

    const isPdf = file.type === 'application/pdf' || file.name.toLowerCase().endsWith('.pdf');

    if (!isPdf) {
      this.selectedFile = null;
      this.errorMessage = 'El archivo debe ser un PDF.';
      this.cdr.detectChanges();
      return;
    }

    this.selectedFile = file;
    this.cdr.detectChanges();
  }
}