import { Component, Input } from '@angular/core';
import SportActivitySummary from '../../models/SportActivitySummary';

@Component({
  selector: 'app-activity-summary-item',
  imports: [],
  templateUrl: './activity-summary-item.html',
  styleUrl: './activity-summary-item.css'
})
export class ActivitySummaryItem {
  @Input({required: true}) activity!: SportActivitySummary;
}
