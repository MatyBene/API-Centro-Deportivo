import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ActivityService } from '../../services/activity-service';
import SportActivity from '../../models/SportActivity';

@Component({
  selector: 'app-activity-detail-page',
  imports: [CommonModule, RouterLink],
  templateUrl: './activity-detail-page.html',
  styleUrl: './activity-detail-page.css'
})
export class ActivityDetailPage implements OnInit {
  activity! : SportActivity;
  isLoading: boolean = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private activityService : ActivityService,
    private router: Router
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
          this.activity = data,
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
  getInstructor(instructorId: number): void{
    this.router.navigate(['/instructors', instructorId])
  }
}
