import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-topbar',
  standalone: true,
  templateUrl: './topbar.html',
  styleUrls: ['./topbar.css']
})
export class Topbar {
  @Output() openCorpus = new EventEmitter<void>();

  onBrandClick() {
    window.location.reload();
  }

  onOpenCorpus() {
    this.openCorpus.emit();
  }
}