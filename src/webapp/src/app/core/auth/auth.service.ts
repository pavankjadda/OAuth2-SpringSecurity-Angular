import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs";
import {map} from "rxjs/operators";
import {OAUTH2_ACCESS_TOKEN_URI, OAUTH2_CLIENT_ID, OAUTH2_CLIENT_SECRET, SERVER_API_URL} from "../../app.constants";
import {User} from "../user/model/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService
{
  constructor(private httpClient: HttpClient)
  {
    this.currentUserSubject=new BehaviorSubject<User>( JSON.parse( localStorage.getItem( 'currentUser' ) ) );
    this.currentUser=this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User
  {
    return this.currentUserSubject.value;
  }

  redirectUrl: string;
  public currentUser: Observable<User>;
  public currentUserSubject: BehaviorSubject<User>;

  static isUserLoggedIn(): boolean
  {
    return localStorage.getItem( 'isLoggedIn' )==='true';
  }


  // @ts-ignore
  login(username: string, password: string): Observable<any>
  {
    const httpOptions={
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          authorization: 'Basic '+btoa( username+':'+password )
        } )
    };
    //return this.httpClient.get<any>( SERVER_API_URL+'login', httpOptions );


    return this.httpClient.get<any>( SERVER_API_URL+'login', httpOptions)
      .pipe( map( user =>
      {
        // login successful if there's a Spring Session token in the response
        if(user && user.token)
        {
          // store user details and Spring Session token in local storage to keep user logged in between page refreshes
          localStorage.setItem( 'currentUser', JSON.stringify( user ) );
          localStorage.setItem( 'isLoggedIn', 'true' );
          this.currentUserSubject.next( user );
        }
        return user;
      }));
  }

  // @ts-ignore
  oauthLogin(username: string, password: string): Observable<any>
  {
    const httpOptions={
      headers: new HttpHeaders(
        {
          "Content-Type": "application/x-www-form-urlencoded",
          authorization: 'Basic '+btoa( OAUTH2_CLIENT_ID+':'+OAUTH2_CLIENT_SECRET )
        })
    };

    const body = new HttpParams()
      .set("grant_type", "password")
      .set('username', username)
      .set('password', password)
      .set("client_id", "spring-security-oauth2-read-write-client");

    return this.httpClient.post<any>(OAUTH2_ACCESS_TOKEN_URI, body.toString(), httpOptions)
               .pipe(response =>
               {
                 //login successful if there's a Spring Session token in the response
                 if (response && response["access_token"])
                 {
                   //store user details and Spring Session OAuth token refreshes
                   localStorage.setItem("access_token", response["access_token"]);
                   localStorage.setItem("refresh_token", response["access_token"]);
                   localStorage.setItem("token_type", response["token_type"]);
                   localStorage.setItem("scope", response["scope"]);
                   localStorage.setItem("isLoggedIn", "true");
                 }
                 return response;
               });
  }
  logout()
  {
    localStorage.setItem("access_token", null);
    localStorage.setItem("refresh_token", null);
    localStorage.setItem("token_type", null);
    localStorage.setItem("scope", null);

    localStorage.removeItem( 'currentUser' );
    this.currentUserSubject.next( null );
    localStorage.setItem( 'isLoggedIn', 'false' );
  }

}
