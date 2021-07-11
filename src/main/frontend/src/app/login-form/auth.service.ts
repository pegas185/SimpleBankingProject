import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {LoginRequest} from "./login-request";
import {catchError, map, tap} from "rxjs/operators";

class User {
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public readonly LOGIN_PATH = '/login';
  public readonly INITIAL_PATH = '/accounts';
  private loggedUser?: User;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
  }

  login(loginRequest: LoginRequest): Observable<User> {

    const headers = new HttpHeaders(loginRequest ? {
      authorization: 'Basic ' + btoa(loginRequest.username + ':' + loginRequest.password)
    } : {});

    return this.http.get<any>('user', {headers: headers})
      .pipe(tap(data => this.doLoginUser(data)));
  }

  doLoginUser(user: User): void {
    this.loggedUser = user;
  }

  doLogoutUser(): void {
    this.loggedUser = undefined;
  }

  logout() {
    return this.http.get<any>(`/logout`)
      .pipe(tap(() => this.doLogoutUser()));
  }

  isLoggedIn(): Observable<boolean> {
    return this.getCurrentUser().pipe(
      map(user => !!user),
      catchError(() => of(false))
    );
  }

  getCurrentUser(): Observable<User> {
    if (this.loggedUser) {
      return of(this.loggedUser);
    } else {
      return this.http.get<User>(`/user`)
        .pipe(tap(user => this.loggedUser = user));
    }
  }

}
