import { Routes } from '@angular/router';
import { HomePage } from './pages/home-page/home-page';
import { FormPage } from './pages/form-page/form-page';

export const routes: Routes = [
    {path: '', component: HomePage},
    {path: 'public/register', component: FormPage}
];
