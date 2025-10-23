import { Component, OnInit } from '@angular/core';
import { ActivityService } from '../../services/activity-service';
import SportActivitySummary from '../../models/SportActivitySummary';
import { ActivitySummaryItem } from '../../components/activity-summary-item/activity-summary-item';
import { Pagination } from '../../components/pagination/pagination';

@Component({
  selector: 'app-activity-list-page',
  imports: [ActivitySummaryItem, Pagination],
  templateUrl: './activity-list-page.html',
  styleUrl: './activity-list-page.css'
})
export class ActivityListPage implements OnInit{
  activities: SportActivitySummary[] = [];
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages!: number;
  isLoading: boolean = false;

  constructor(private activityService: ActivityService){}

  ngOnInit(): void {
    this.loadActivities();
  }

  loadActivities(){
    this.isLoading = true;
    this.activityService.getActivities(this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.activities = data.content;
        this.totalPages = data.totalPages;
        this.isLoading = false;
      },
      error: (e) => {
        console.log('Error: ', e);
        this.isLoading = false;
      }
    })
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.loadActivities();
  }

  nextPage() {
    if(this.currentPage < this.totalPages - 1){
      this.currentPage++;
      this.loadActivities();
    }
  }

  previousPage() {
    if(this.currentPage > 0){
      this.currentPage--;
      this.loadActivities();
    }
  }

  get hasNextPage(): boolean {
    return this.currentPage < this.totalPages - 1;
  }

  get hasPreviousPage(): boolean {
    return this.currentPage > 0;
  }
}
