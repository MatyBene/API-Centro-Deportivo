import { Component, OnInit } from '@angular/core';
import SportActivitySummary from '../../models/SportActivitySummary';
import { MemberService } from '../../services/member-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-member-activities-page',
  imports: [RouterLink],
  templateUrl: './member-activities-page.html',
  styleUrl: './member-activities-page.css'
})
export class MemberActivitiesPageComponent implements OnInit {

  activities: SportActivitySummary[] = [];
  isLoading: boolean = true;
  error: string | null = null;

  constructor(private memberService: MemberService) { }

  ngOnInit(): void {
    this.loadActivities();
  }

  loadActivities(): void {
    this.isLoading = true;
    this.memberService.getEnrolledActivities().subscribe({
      next: (data) => {
        this.activities = data;
        this.isLoading = false;
        this.error = null;
        if (data && data.length > 0) {
        console.log('Estructura del primer objeto de actividad/inscripción:', data[0]);
      }
      },
      error: (e) => {
        console.error('Error al cargar actividades:', e);
        this.error = 'No pudimos cargar tus actividades inscritas. Por favor, intenta más tarde.'; 
        this.isLoading = false;
      }
    });
  }
}