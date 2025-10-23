import { Routes } from '@angular/router';
import { HomePage } from './pages/home-page/home-page';
import { FormPage } from './pages/form-page/form-page';
import { LoginPage } from './pages/login-page/login-page';
import { ProfilePage } from './pages/profile-page/profile-page';
import { guestGuard } from './guards/guest-guard';
import { authGuard } from './guards/auth-guard';
import { ActivityListPage } from './pages/activity-list-page/activity-list-page';
import { ActivityDetailPage } from './pages/activity-detail-page/activity-detail-page';

export const routes: Routes = [
    {path: '', component: HomePage},
    {path: 'public/login', component: LoginPage, canActivate: [guestGuard]},
    {path: 'public/register', component: FormPage, canActivate: [guestGuard]},

    {path: 'activity-list', component: ActivityListPage},
    {path: 'activity-list/:id', component: ActivityDetailPage},

    {path: 'members/profile', component: ProfilePage, canActivate: [authGuard]}
];
