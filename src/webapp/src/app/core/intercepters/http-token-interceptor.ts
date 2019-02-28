import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {AuthService} from "../auth/auth.service";

@Injectable()
export class HttpTokenInterceptor implements HttpInterceptor
{
  constructor(private authService: AuthService, private router: Router)
  {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
  {
    let currentUser=this.authService.currentUserValue;
    if(currentUser&&currentUser.token)
    {
      request=request.clone(
        {
          setHeaders: {
            'X-Auth-Token': currentUser.token
          }
        } );
    }

    return next.handle( request );
  }
}
