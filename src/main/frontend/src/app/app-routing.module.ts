import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from "./login-form/login-form.component";
import {AuthGuard} from "./login-form/auth-guard";
import {AppGuard} from "./login-form/app-guard";
import {AccountsComponent} from "./accounts/accounts.component";

const routes: Routes = [
  {path: 'login', component: LoginFormComponent, canActivate: [AuthGuard]},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'accounts', component: AccountsComponent, pathMatch: 'full', canActivate: [AppGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
