import { Component, Input } from '@angular/core';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-field-error',
  imports: [],
  templateUrl: './field-error.html',
  styleUrl: './field-error.css'
})
export class FieldError {
  @Input() control: AbstractControl | null = null;
  @Input() serverError?: string;
  @Input() customErrors?: {[key: string]: string};

  get errorMessage(): string | null {
    if (!this.control || !this.control.errors || !this.control.touched) {
      return this.serverError || null;
    }

    const errors = this.control.errors;
    const errorMessages: { [key: string]: string } = {
      'required': 'Este campo es obligatorio',
      'email': 'El email debe tener un formato válido',
      'minlength': `Debe tener mínimo ${errors['minlength']?.requiredLength} caracteres`,
      'maxlength': `Debe tener máximo ${errors['maxlength']?.requiredLength} caracteres`,
      'pattern': 'El formato es inválido',
      ...this.customErrors
    };

    const firstError = Object.keys(errors)[0];
    return errorMessages[firstError] || 'Error de validación';
  }
}
