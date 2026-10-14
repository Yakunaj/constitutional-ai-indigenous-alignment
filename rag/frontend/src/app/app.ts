import { Component } from '@angular/core';
import { Topbar } from './components/topbar/topbar';
import { Chat } from './components/chat/chat';
import { CorpusModal} from './components/corpus-modal/corpus-modal';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Topbar, Chat, CorpusModal],
  templateUrl: './app.html'
})
export class App {
  isModalOpen = false;
}