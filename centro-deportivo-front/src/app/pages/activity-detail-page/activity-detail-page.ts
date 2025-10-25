import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import SportActivitySummary from '../../models/SportActivitySummary';
import { ActivityService } from '../../services/activity-service';

@Component({
  selector: 'app-activity-detail-page',
  imports: [CommonModule, RouterLink],
  templateUrl: './activity-detail-page.html',
  styleUrl: './activity-detail-page.css'
})
export class ActivityDetailPage implements OnInit {
  activity! : SportActivitySummary;
  isLoading: boolean = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private activityService : ActivityService
  ){}

  ngOnInit(): void {
    this.loadActivityDetail();
  }
  
  loadActivityDetail(): void{
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam ? +idParam : null;

    if (id){
      this.isLoading = true;
      this.activityService.getActivity(id).subscribe({
        next: (data) => {
          this.activity = data;
          this.isLoading = false;
        },
        error: (e) => {
          console.error('Error al obtener el detalle: ', e);
          this.error = 'No se pudo cargar el detalle de la actividad.';
          this.isLoading = false;
        }
      })
    } else {
        this.error = 'ID de actividad no válido.';
        this.isLoading = false;
    }
  }


}
