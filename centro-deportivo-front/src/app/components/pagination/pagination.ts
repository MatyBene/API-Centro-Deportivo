import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  imports: [],
  templateUrl: './pagination.html',
  styleUrl: './pagination.css'
})
export class Pagination {
  @Input() currentPage!: number;
  @Input() totalPages!: number;
  @Input() hasPreviousPage!: boolean;
  @Input() hasNextPage!: boolean;

  @Output() previousPageEvent = new EventEmitter<void>();
  @Output() nextPageEvent = new EventEmitter<void>();

  onPreviousPage() {
    this.previousPageEvent.emit();
  }

  onNextPage() {
    this.nextPageEvent.emit();
  }

}
